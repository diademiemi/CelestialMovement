package dev.queercoded.spigottemplate;

import dev.queercoded.spigottemplate.command.CommandExec;
import dev.queercoded.spigottemplate.listener.BlockBreakEventListener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotTemplate extends JavaPlugin {

    /**
     * Plugin Instance
     */
    private static SpigotTemplate plugin;

    /**
     * Plugin Manager
     */
    private static PluginManager pm;

    /**
     * Run when plugin is enabled
     */
    @Override
    public void onEnable() {
        // Plugin startup logic

        // Set plugin instance
        plugin = this;

        // Set plugin manager
        pm = getServer().getPluginManager();

        // Register commands
        getCommand("spigottemplate").setExecutor(new CommandExec()); // Change "spigottemplate" to your commands label

        // Register event listeners, change "BlockBreakEventListener" to the listener you want to register
        pm.registerEvents(new BlockBreakEventListener(), this); // Uncomment to register event listener

        // Register permissions
        pm.addPermission(new Permission("spigottemplate.command")); // Change "spigottemplate.command" to a permission node
        // A player can be checked for this with player.hasPermission("spigottemplate.command")

    }

    /**
     * Run when plugin is disabled
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Set plugin instance to null
        plugin = null;
    }

    /**
     * @return SpigotTemplate - Plugin instance
     */
    public static SpigotTemplate getPlugin() {
        return plugin;
    }

}
