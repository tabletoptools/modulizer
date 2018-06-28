package io.tabletoptools.discord.modulizer;

import io.tabletoptools.discord.modulizer.annotation.Command;
import io.tabletoptools.discord.modulizer.annotation.Option;
import io.tabletoptools.discord.modulizer.annotation.Parameter;
import io.tabletoptools.discord.modulizer.annotation.Required;
import io.tabletoptools.discord.modulizer.modules.config.ConfigModule;
import io.tabletoptools.discord.modulizer.modules.help.HelpModule;
import io.tabletoptools.discord.modulizer.modules.modulizer.ModulizerModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Modulizer {

    /*
    *
    * Modules can be loaded or unloaded at runtime
    * Modules can be enabled and disabled on a per-user and per-server basis
    * Modules contain a set of functionality and already have implied access to the following:
    *  - Server- (Guild-)Events
    *  - Persistence
    *  - Logging
    *
    *  Alternatively:
    *  - The Module defines what it needs and the implementor is responsible for making the corresponding resources available
    *  - For this, Modulizer would provide a way to share something with the module at load time
    *
    *  Per-user and per-server accessibility is stored in persistence, not in memory
    *  Module functionality can be restricted to 'roles' on two levels:
    *  - Source-Code level (Static)
    *  - Dynamically, using commands
    *
    *  Modules are loaded automatically depending on existing configuration.
    *
    *  Help
    *  - A module provides help
    *  - Help gives a description of the module, and lists sub-modules and commands
    *
    *  Sub-Modules:
    *  - Modules can have Sub-Modules
    *  - Sub-Modules extend the command level by one
    *
    *  Configuration
    *  - A Module can store configuration in the following scope:
    *  - Global
    *  - Module-Specific
    *  - User-Specific
    *  - Server-Specific
    *  - User-Server-Specific
    *  - Module-User-Specific
    *  - Module-Server-Specific
    *  - Module-Server-User-Specific
    *  - Channel-Specific
    *  - User-Channel-Specific
    *  - Module-Channel-Specific
    *  - Module-Channel-User-Specific
    *
    *  Persistence is provided by the implementation of the module
    *
    * TODO: Interceptors
    * TODO: Jobs
    *
    */

    //loaded modules
    private static Modulizer instance;

    private ArrayList<Module> modules;

    public static Modulizer instance() {
        if(instance == null) {
            instance = new Modulizer();
        }
        return instance;
    }

    public Modulizer() {
        this.modules = new ArrayList<>();
        this.modules.add(new HelpModule());
        this.modules.add(new ModulizerModule());
        this.modules.add(new ConfigModule());
        this.modules.forEach(Module::load);
        this.modules.forEach(Module::enable);
    }

    public Modulizer loadModule(Module module, Boolean enable) {
        this.modules.add(module);
        module.load();
        if(enable) module.enable();
        return this;
    }

    public Modulizer loadModule(Module module) {
        return this.loadModule(module, true);
    }

    public void stop() {
        this.modules.forEach(Module::disable);
        this.modules.forEach(Module::unload);
        this.modules.clear();
    }

    public void process(MessageReceivedEvent event) {
        try {
            if (isCommand(event.getMessage().getContentRaw())) {

                List<String> commands = getCommands(event.getMessage().getContentRaw());
                
                Optional<MethodMatch> commandMethodMatch = findCommandMethod(commands);

                if(!commandMethodMatch.isPresent()) return;

                Object commandClass = commandMethodMatch.get().getMethod().getDeclaringClass().getConstructors()[0].newInstance();

                ((CommandClass)commandClass).setEvent(event);
                ((CommandClass)commandClass).setModule(commandMethodMatch.get().getModule());

                //TODO: Run interceptors here
                List<String> commandsCopy = getCommands(event.getMessage().getContentRaw());

                /*
                * Module / Command format example:
                * /help/list/
                * or
                * /commands/add/something/
                * (note the '/' at the beginning and end of a formatted command string)
                */

                //TODO: Command Arguments
                //Method Match may change depending on the arguments
                commandMethodMatch.get().getMethod().invoke(commandClass);

            }
        } catch (InvocationTargetException ex) {
            Logger.MODULIZER_LOG.warn("Error Invoking Command: ", ex);
        } catch (InstantiationException ex) {
            Logger.MODULIZER_LOG.warn("Error Instantiating Command Class: ", ex);
        } catch (IllegalAccessException ex) {
            Logger.MODULIZER_LOG.warn("Illegal Access Exception Instantiating Command Class: ", ex);
        }


    }

    private Optional<MethodMatch> findCommandMethod(List<String> commands) {

        List<MethodMatch> matches = new ArrayList<>();

        if(getCommand(commands, 0) != null) this.modules.forEach(module -> {
            if(!module.getClass().isAnnotationPresent(Command.class)) {
                matches.addAll(module.getMethodMatches(commands, 0));
            }
            else if(Arrays.stream(module.getClass()
                    .getAnnotation(Command.class)
                    .value()
            ).anyMatch(alias -> alias.equals(getCommand(commands, 0)))) {
                matches.addAll(module.getMethodMatches(commands, 1));
            }
        });

        //TODO: Filter for most matching method

        return matches.stream()
                .sorted(Comparator.comparing(MethodMatch::getDepth))
                /*.filter(methodMatch -> {

                    //Get all parameters for method depending on depth

                    //Process parameters, sorting them

                    //Match parameters with available arguments

                    //methodMatch.getMethod().getParameters()
                    return false;
                })*/
                .findFirst();

    }
    
    public List<String> getCommands(String message) {

        //TODO: Check prefix length
        message = message.trim().substring(1);

        return new ArrayList<>(Arrays.asList(message.split(" ")[0].split(".")));

    }

    private boolean methodMatch(Method method, List<String> arguments) {
        //Is method a command?
        assert isCommand(method) : false;
        Options options = new Options();
        Arrays
                .stream(method.getParameters())
                .forEach(parameter -> {
                    Parameter paramNames = parameter.getAnnotation(Parameter.class);
                    org.apache.commons.cli.Option option = org.apache.commons.cli.Option.builder()
                            .required(parameter.isAnnotationPresent(Required.class))
                            .argName(paramNames.value())
                            .longOpt("".equals(paramNames.longName()) ? paramNames.longName() : null)
                            .build();
                    options.addOption(option);

                });
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            commandLineParser.parse(options, arguments.toArray(new String[0]), true);
            return true;
        }
        catch (ParseException ex) {
            return false;
        }
    }

    private boolean isCommand(String message) {
        //TODO: Check for Prefix according to server settings
        return message.startsWith("/");
    }

    private boolean isCommand(Method method) {
        return method.isAnnotationPresent(Command.class);
    }

    private String getCommand(List<String> commands, Integer depth) {
        return commands.size() > depth ? commands.get(depth) : null;
    }

}
