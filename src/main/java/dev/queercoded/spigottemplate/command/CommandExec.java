package dev.queercoded.spigottemplate.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        /*
            An example command, replace this with your own command logic
            This will send a message to the sender when the command is executed with the label and arguments
            When "test" is given, it will return "Test command executed" to the sender
            When "echo" is given, it will return the arguments to the sender
            It always returns true to indicate the command was executed successfully to the server
         */

        // Check if the sender has the permission defined earlier
        if (!sender.hasPermission("spigottemplate.command")) {
            sender.sendMessage("You do not have permission to execute this command");
            return true;
        }
        if (args.length == 0) {
            // No arguments
            sender.sendMessage("No arguments given");
            return true;
        }
        switch (args[0]) {
            case "test":
                // Do stuff
                if (args.length > 1) {
                    // Do stuff with arguments
                    switch (args[1]) {
                        case "test2":
                            // Do stuff
                            sender.sendMessage("Test2 command executed");
                            return true;
                        default:
                            // Do stuff
                            break;
                    }
                }
                sender.sendMessage("Test command executed");
                break;
            case "echo":
                // Do stuff
                if (args.length > 1) {
                    // Do stuff with arguments
                    // Remove first argument
                    String[] newArgs = new String[args.length - 1];
                    System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                    sender.sendMessage(String.join(" ", newArgs));
                }
                break;
            default:
                // Do stuff
                sender.sendMessage("Invalid argument");
                break;
        }
        return true;
    }
}
