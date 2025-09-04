package net.waterwolf;


import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.waterwolf.commands.*;
import net.waterwolf.events.*;


public final class SG30DLL extends JavaPlugin {
    // public FileConfiguration config = getConfig();
    private static GUIHandler CompressGUI;
    private static Economy econ = null;
    private static Permission perms = null;
    private static SG30DLL plugin;


    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        FusionLib.sendToConsole("\n&x&0&8&4&c&f&b█&x&1&1&4&9&f&a█&x&1&a&4&6&f&9█&x&2&3&4&4&f&8█&x&2&c&4&1&f&6█&x&3&5&3&e&f&5█&x&3&e&3&b&f&4█&x&4&8&3&8&f&3╗ &x&5&1&3&5&f&2█&x&5&a&3&3&f&1█&x&6&3&3&0&f&0█&x&6&c&2&d&e&e█&x&7&5&2&a&e&d█&x&7&e&2&7&e&c█&x&8&7&2&5&e&b╗    &x&9&0&2&2&e&a█&x&9&9&1&f&e&9█&x&a&2&1&c&e&7█&x&a&b&1&9&e&6█&x&b&4&1&7&e&5█&x&b&d&1&4&e&4█&x&c&7&1&1&e&3╗ &x&d&0&0&e&e&2█&x&d&9&0&b&e&1█&x&e&2&0&8&d&f╗     &x&e&b&0&6&d&e█&x&f&4&0&3&d&d█&x&f&d&0&0&d&c╗     \n&x&0&8&4&c&f&b█&x&1&0&4&9&f&a█&x&1&9&4&7&f&9╔&x&2&1&4&4&f&8═&x&2&a&4&2&f&7═&x&3&2&3&f&f&6═&x&3&b&3&c&f&5═&x&4&3&3&a&f&4╝&x&4&c&3&7&f&2█&x&5&4&3&4&f&1█&x&5&c&3&2&f&0╔&x&6&5&2&f&e&f═&x&6&d&2&d&e&e═&x&7&6&2&a&e&d═&x&7&e&2&7&e&c═&x&8&7&2&5&e&b╝    &x&8&f&2&2&e&a█&x&9&8&1&f&e&9█&x&a&0&1&d&e&8╔&x&a&9&1&a&e&7═&x&b&1&1&8&e&6═&x&b&9&1&5&e&5█&x&c&2&1&2&e&3█&x&c&a&1&0&e&2╗&x&d&3&0&d&e&1█&x&d&b&0&a&e&0█&x&e&4&0&8&d&f║     &x&e&c&0&5&d&e█&x&f&5&0&3&d&d█&x&f&d&0&0&d&c║     \n&x&0&8&4&c&f&b█&x&1&1&4&9&f&a█&x&1&b&4&6&f&9█&x&2&4&4&3&f&7█&x&2&e&4&0&f&6█&x&3&7&3&d&f&5█&x&4&1&3&a&f&4█&x&4&a&3&8&f&3╗&x&5&3&3&5&f&1█&x&5&d&3&2&f&0█&x&6&6&2&f&e&f║  &x&7&0&2&c&e&e█&x&7&9&2&9&e&d█&x&8&3&2&6&e&c█&x&8&c&2&3&e&a╗   &x&9&5&2&0&e&9█&x&9&f&1&d&e&8█&x&a&8&1&a&e&7║  &x&b&2&1&7&e&6█&x&b&b&1&4&e&4█&x&c&4&1&2&e&3║&x&c&e&0&f&e&2█&x&d&7&0&c&e&1█&x&e&1&0&9&e&0║     &x&e&a&0&6&d&e█&x&f&4&0&3&d&d█&x&f&d&0&0&d&c║     \n&x&0&8&4&c&f&b╚&x&1&2&4&9&f&a═&x&1&c&4&6&f&9═&x&2&5&4&3&f&7═&x&2&f&4&0&f&6═&x&3&9&3&d&f&5█&x&4&3&3&a&f&4█&x&4&d&3&7&f&2║&x&5&6&3&4&f&1█&x&6&0&3&1&f&0█&x&6&a&2&e&e&f║   &x&7&4&2&b&e&d█&x&7&e&2&8&e&c█&x&8&7&2&4&e&b║   &x&9&1&2&1&e&a█&x&9&b&1&e&e&8█&x&a&5&1&b&e&7║  &x&a&f&1&8&e&6█&x&b&8&1&5&e&5█&x&c&2&1&2&e&3║&x&c&c&0&f&e&2█&x&d&6&0&c&e&1█&x&e&0&0&9&e&0║     &x&e&9&0&6&d&e█&x&f&3&0&3&d&d█&x&f&d&0&0&d&c║     \n&x&0&8&4&c&f&b█&x&0&e&4&a&f&a█&x&1&3&4&8&f&a█&x&1&9&4&7&f&9█&x&1&f&4&5&f&8█&x&2&4&4&3&f&7█&x&2&a&4&1&f&7█&x&3&0&4&0&f&6║&x&3&6&3&e&f&5╚&x&3&b&3&c&f&5█&x&4&1&3&a&f&4█&x&4&7&3&9&f&3█&x&4&c&3&7&f&2█&x&5&2&3&5&f&2█&x&5&8&3&3&f&1█&x&5&d&3&1&f&0╔&x&6&3&3&0&e&f╝&x&6&9&2&e&e&f█&x&6&f&2&c&e&e█&x&7&4&2&a&e&d╗&x&7&a&2&9&e&d█&x&8&0&2&7&e&c█&x&8&5&2&5&e&b█&x&8&b&2&3&e&a█&x&9&1&2&2&e&a█&x&9&6&2&0&e&9█&x&9&c&1&e&e&8╔&x&a&2&1&c&e&8╝&x&a&8&1&b&e&7█&x&a&d&1&9&e&6█&x&b&3&1&7&e&5█&x&b&9&1&5&e&5█&x&b&e&1&3&e&4█&x&c&4&1&2&e&3█&x&c&a&1&0&e&2█&x&c&f&0&e&e&2╗&x&d&5&0&c&e&1█&x&d&b&0&b&e&0█&x&e&1&0&9&e&0█&x&e&6&0&7&d&f█&x&e&c&0&5&d&e█&x&f&2&0&4&d&d█&x&f&7&0&2&d&d█&x&f&d&0&0&d&c╗\n&x&0&8&4&c&f&b╚&x&0&e&4&a&f&a═&x&1&4&4&8&f&9═&x&1&a&4&6&f&9═&x&2&1&4&4&f&8═&x&2&7&4&3&f&7═&x&2&d&4&1&f&6═&x&3&3&3&f&f&6╝ &x&3&9&3&d&f&5╚&x&3&f&3&b&f&4═&x&4&5&3&9&f&3═&x&4&b&3&7&f&2═&x&5&2&3&5&f&2═&x&5&8&3&3&f&1═&x&5&e&3&1&f&0╝ &x&6&4&3&0&e&f╚&x&6&a&2&e&e&f═&x&7&0&2&c&e&e╝&x&7&6&2&a&e&d╚&x&7&c&2&8&e&c═&x&8&3&2&6&e&c═&x&8&9&2&4&e&b═&x&8&f&2&2&e&a═&x&9&5&2&0&e&9═&x&9&b&1&e&e&8╝ &x&a&1&1&d&e&8╚&x&a&7&1&b&e&7═&x&a&d&1&9&e&6═&x&b&4&1&7&e&5═&x&b&a&1&5&e&5═&x&c&0&1&3&e&4═&x&c&6&1&1&e&3═&x&c&c&0&f&e&2╝&x&d&2&0&d&e&1╚&x&d&8&0&b&e&1═&x&d&e&0&a&e&0═&x&e&5&0&8&d&f═&x&e&b&0&6&d&e═&x&f&1&0&4&d&e═&x&f&7&0&2&d&d═&x&f&d&0&0&d&c╝ ", false);
        FusionLib.sendToConsole("Hooking into Vault...", true);
        // Bukkit.getConsoleSender().sendMessage(ChatColor.of("#331122") + "testing hex colors");
        if (!setupEconomy() ) {         
            Bukkit.getLogger().severe(String.format(FusionLib.prefix + " - Disabled due to no Vault dependency found!", getDescription().getName()));
            FusionLib.sendToConsole("Please Forgive Me", true);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        FusionLib.sendToConsole("Hooked into Vault", true);
        FusionLib.sendToConsole("Loading events...", true);
        registerEvents();
        CompressGUI = new GUIHandler(this);
        FusionLib.sendToConsole("Loaded events!", true);

        FusionLib.sendToConsole("Loading commands...", true);
        registerCommands();
        FusionLib.sendToConsole("Loaded commands!", true);

        FusionLib.sendToConsole("Plugin finished loading!",true);

        // config.addDefault("youAreAwesome", true);
        // config.options().copyDefaults(true);
        // saveConfig();

    }

    public void registerEvents() {
        // Register the events here!
        new OnItemPickup(this);
        new OnPlayerJoin(this);


    }

    public void registerCommands() {
        // Register the commands here!
        new Compress(this);
        new storeHeldItem(this);
        new restoreItem(this);
        new listSavedItems(this);
    }

    @Override
    public void onDisable() {
        FusionLib.sendToConsole("Plugin disabled : ", true);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }

    public static SG30DLL getPlugin(){
        return plugin;
    }

    public static GUIHandler getCompressGUI() { return CompressGUI; }
}


