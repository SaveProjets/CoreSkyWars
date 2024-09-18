package fr.edmine.SkyWars;

import org.bukkit.plugin.PluginManager;

public class Register {
    private final Main plugin;

    public Register(Main plugin) {
        this.plugin = plugin;
    }

    public void registerEvents() {
        PluginManager pm = plugin.getServer().getPluginManager();
    }
}
