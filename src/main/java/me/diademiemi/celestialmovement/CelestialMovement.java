package me.diademiemi.celestialmovement;

import me.diademiemi.celestialmovement.command.CommandExec;
import me.diademiemi.celestialmovement.listener.MovementListener;
import me.diademiemi.celestialmovement.persistentdata.DataLoader;
import me.diademiemi.celestialmovement.persistentdata.PlayerPreferences;
import me.diademiemi.celestialmovement.persistentdata.PreferenceList;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CelestialMovement extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(PlayerPreferences.class);
        ConfigurationSerialization.registerClass(PreferenceList.class);
    }

    /**
     * Plugin Instance
     */
    private static CelestialMovement plugin;

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
        getCommand("celestial").setExecutor(new CommandExec());

        pm.registerEvents(new MovementListener(), this);

        // Register permissions
        pm.addPermission(new Permission("celestial.wallclimb"));
        pm.addPermission(new Permission("celestial.walljump"));
        pm.addPermission(new Permission("celestial.dash"));
        pm.addPermission(new Permission("celestial.dash.double"));
        pm.addPermission(new Permission("celestial.dash.unlimited"));

        DataLoader.init();
        DataLoader.readData();

    }

    /**
     * Run when plugin is disabled
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DataLoader.writeData();

        // Set plugin instance to null
        plugin = null;
    }

    /**
     * @return SpigotTemplate - Plugin instance
     */
    public static CelestialMovement getPlugin() {
        return plugin;
    }

}
