package me.diademiemi.celestialmovement.persistentdata;

import me.diademiemi.celestialmovement.CelestialMovement;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DataLoader {

    private static File dataFile; // File to store data in

    private static YamlConfiguration data; // Get the data from the data file as a YamlConfiguration

    /**
     * @return YamlConfiguration - Data from the data file
     */
    public static YamlConfiguration getData() {
        return data;
    }

    /**
     * Initialise the data file
     */
    public static void init() {
        dataFile = new File(CelestialMovement.getPlugin().getDataFolder(), "data.yml"); // Set data file
        if (!dataFile.exists()) { // If data file does not exist, create it
            dataFile.getParentFile().mkdirs();
            CelestialMovement.getPlugin().saveResource("data.yml", false); // Don't replace existing file
        }
    }

    /**
     * Write data to file
     */
    public static void writeData() {
        data.set("playerPrefs", PreferenceList.getInstance());

        // We don't need to set the velocity, because it is already set in the data variable.

        try {
            data.save(dataFile); // Attempt to save data to file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data from the data file
     */
    public static void readData() {
        data = YamlConfiguration.loadConfiguration(dataFile); // This will call the constructor for PlayerDisabledList. See the constructor for more info.

        if (PreferenceList.getPreferencesMap() == null) { // If the list is null, then the constructor was not called. This means that the data file is empty.
            CelestialMovement.getPlugin().getLogger().info("Creating new disabled player list");
            new PreferenceList(); // This calls the constructor, which will create a new list.
        }

    }

}