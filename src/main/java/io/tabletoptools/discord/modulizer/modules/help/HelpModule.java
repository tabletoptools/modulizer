package io.tabletoptools.discord.modulizer.modules.help;

import io.tabletoptools.discord.modulizer.Module;
import io.tabletoptools.discord.modulizer.annotation.Command;
import io.tabletoptools.discord.modulizer.annotation.Locked;

@Locked()
@Command("help")
public class HelpModule extends Module {

    @Override
    public void onLoad() {
        this.addCommandClass(HelpCommands.class);
    }

    @Override
    public void onUnload() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }



}
