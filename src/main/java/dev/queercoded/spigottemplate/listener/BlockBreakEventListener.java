package dev.queercoded.spigottemplate.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent; // Change "BlockBreakEvent" to the event you want to listen for

public class BlockBreakEventListener implements Listener { // Change "BlockBreakEventListener" to the name of your listener

    /**
     * Constructor
     */
    public BlockBreakEventListener() {
        // Constructor
    }

    /**
     * @param e BlockBreakEvent - Event data
     */
    @EventHandler(priority=EventPriority.NORMAL) // Change priority if needed
    public void onBlockBreak(BlockBreakEvent e) { // Replace "BlockBreakEvent" with the event you want to listen for
        // Do stuff

        // Some Example Code:
        Player player = e.getPlayer();
        Block block = e.getBlock();

        // Send message to player
        player.sendMessage("You broke a " + block.getType().toString());

    }
}
