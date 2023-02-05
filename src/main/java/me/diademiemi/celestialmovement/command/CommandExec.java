package me.diademiemi.celestialmovement.command;

import me.diademiemi.celestialmovement.persistentdata.PlayerPreferences;
import me.diademiemi.celestialmovement.persistentdata.PreferenceList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExec implements CommandExecutor {

    /**
     * Run when command is executed, must result a boolean for success
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String... args) {

        if (args.length == 0) {
            // No arguments
            sender.sendMessage("No arguments given");
            return true;
        }
        Player p;
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }
        PlayerPreferences preferences = PreferenceList.getPlayerPreferences(p);
        if (!preferences.getCustom()) {
            preferences = new PlayerPreferences();
            preferences.setCustom(true);
            PreferenceList.addPlayerPreferences(p, preferences);
        }
        switch (args[0]) {
            case "dash":
                if (args.length > 1) {
                    switch (args[1]) {
                        case "toggle":
                            preferences.setDash(!preferences.getDash());
                            p.sendMessage("Dash enabled: " + preferences.getDash() + PreferenceList.getPlayerPreferences(p).getDash());
                            if (!preferences.getDash()) {
                                p.setAllowFlight(false);
                            }
                            break;
                        case "on":
                            preferences.setDash(true);
                            p.sendMessage("Dash enabled: " + preferences.getDash());
                            break;
                        case "off":
                            preferences.setDash(false);
                            p.sendMessage("Dash enabled: " + preferences.getDash());
                            p.setAllowFlight(false);
                            break;
                        case "double":
                            if (args.length > 2) {
                                switch (args[2]) {
                                    case "toggle":
                                        preferences.setDoubleDash(!preferences.getDoubleDash());
                                        p.sendMessage("Double dash enabled: " + preferences.getDoubleDash());
                                        break;
                                    case "on":
                                        preferences.setDoubleDash(true);
                                        p.sendMessage("Double dash enabled: " + preferences.getDoubleDash());
                                        break;
                                    case "off":
                                        preferences.setDoubleDash(false);
                                        p.sendMessage("Double dash enabled: " + preferences.getDoubleDash());
                                        break;
                                    default:
                                        p.sendMessage("Invalid argument: " + args[2]);
                                        break;
                                }
                            } else {
                                p.sendMessage("Arguments: toggle/on/off");
                            }
                            break;
                        case "unlimited":
                            if (args.length > 2) {
                                switch (args[2]) {
                                    case "toggle":
                                        preferences.setUnlimitedDash(!preferences.getUnlimitedDash());
                                        p.sendMessage("Unlimited dash enabled: " + preferences.getUnlimitedDash());
                                        break;
                                    case "on":
                                        preferences.setUnlimitedDash(true);
                                        p.sendMessage("Unlimited dash enabled: " + preferences.getUnlimitedDash());
                                        break;
                                    case "off":
                                        preferences.setUnlimitedDash(false);
                                        p.sendMessage("Unlimited dash enabled: " + preferences.getUnlimitedDash());
                                        break;
                                    default:
                                        p.sendMessage("Invalid argument: " + args[2]);
                                        break;
                                }
                            } else {
                                p.sendMessage("Arguments: toggle/on/off");
                            }
                            break;
                        default:
                            p.sendMessage("Invalid argument: " + args[1]);
                            break;
                    }
                } else {
                    p.sendMessage("Arguments: toggle/on/off, double on/off/toggle, unlimited on/off/toggle");
                }
                break;
            default:
                // Do stuff
                p.sendMessage("Invalid argument");
                break;
        }
        return true;
    }
}
