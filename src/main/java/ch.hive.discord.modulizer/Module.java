package ch.hive.discord.modulizer;

import java.util.HashMap;

public abstract class Module {

    protected HashMap<String, Module> submodules = new HashMap<>();

    public void load() {
        onLoad();
    }

    public void unload() {
        onUnload();
    }

    public void enable() {
        onEnable();
    }

    public void disable() {
        onDisable();
    }

    public abstract void onLoad();
    public abstract void onUnload();
    public abstract void onEnable();
    public abstract void onDisable();

}
