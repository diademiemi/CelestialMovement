package me.diademiemi.celestialmovement.persistentdata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PreferenceList implements ConfigurationSerializable {

    public static PreferenceList instance;
    public static HashMap<String, PlayerPreferences> preferences;

    @Override
    public Map<String, Object> serialize() {
        // Create a new map
        Map<String, Object> map = new HashMap<>();
        // Add the list of players to the map
        map.put("data", preferences);

        // Return the map
        return map;
    }

    public PreferenceList(Map<String, Object> map) {
        // Create a new instance of this class, this calls the constructor below
        PreferenceList preferenceList = new PreferenceList();
        // Set the disabled players list to the list in the map
        preferenceList.preferences = (HashMap<String, PlayerPreferences>) map.get("data"); // Get the players list from the map, cast it back to an ArrayList<String>

        // We don't need to set the static instance of this class, because the constructor below will already do that.
    }


    public PreferenceList() {
        instance = this;
        // Create a new list
        preferences = new HashMap<>();
        // Set the static instance of this class to this instance
        PreferenceList.preferences = preferences;
    }

    public static PreferenceList getInstance() {
        return instance;
    }

    public static HashMap<String, PlayerPreferences> getPreferencesMap() {
        return preferences;
    }

    public static void setPreferencesMap(HashMap<String, PlayerPreferences> preferences) {
        PreferenceList.preferences = preferences;
    }

    public static void addPlayerPreferences(Player player, PlayerPreferences playerPreferences) {
        preferences.put(player.getUniqueId().toString(), playerPreferences);
    }

    public static PlayerPreferences getPlayerPreferences(Player player) {
        if (preferences.containsKey(player.getUniqueId().toString())) {
            return preferences.get(player.getUniqueId().toString());
        } else {
            return new PlayerPreferences();
        }
    }

}