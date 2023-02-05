package me.diademiemi.celestialmovement.persistentdata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class PlayerPreferences implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        // Create a new map
        Map<String, Object> map = new HashMap<>();
        // Add the list of players to the map
        map.put("wallclimb", wallClimb);
        map.put("walljump", wallJump);
        map.put("dash", dash);
        map.put("dashdouble", doubleDash);
        map.put("dashunlimited", unlimitedDash);

        // Return the map
        return map;
    }

    public PlayerPreferences(Map<String, Object> map) {
        this(
            (boolean) map.get("wallclimb"),
            (boolean) map.get("walljump"),
            (boolean) map.get("dash"),
            (boolean) map.get("dashdouble"),
            (boolean) map.get("dashunlimited"));
    }

    public boolean wallClimb;

    public boolean wallJump;

    public boolean dash;

    public boolean doubleDash;

    public boolean unlimitedDash;

    public boolean custom;

    public PlayerPreferences(boolean wallClimb, boolean wallJump, boolean dash, boolean doubleDash, boolean unlimitedDash) {
        this.wallClimb = wallClimb;
        this.wallJump = wallJump;
        this.dash = dash;
        this.doubleDash = doubleDash;
        this.unlimitedDash = unlimitedDash;
        this.custom = true;
    }

    public PlayerPreferences() {
        this.wallClimb = false;
        this.wallJump = false;
        this.dash = false;
        this.doubleDash = false;
        this.unlimitedDash = false;
        this.custom = false;
    }

    public boolean getWallClimb() {
        return wallClimb;
    }

    public void setWallClimb(boolean wallClimb) {
        this.wallClimb = wallClimb;
    }

    public boolean getWallJump() {
        return wallJump;
    }

    public void setWallJump(boolean wallJump) {
        this.wallJump = wallJump;
    }

    public boolean getDash() {
        return dash;
    }

    public void setDash(boolean dash) {
        this.dash = dash;
    }

    public boolean getDoubleDash() {
        return doubleDash;
    }

    public void setDoubleDash(boolean doubleDash) {
        this.doubleDash = doubleDash;
    }

    public boolean getUnlimitedDash() {
        return unlimitedDash;
    }

    public void setUnlimitedDash(boolean unlimitedDash) {
        this.unlimitedDash = unlimitedDash;
    }

    public boolean getCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }
}
