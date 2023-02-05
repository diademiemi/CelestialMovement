package me.diademiemi.celestialmovement.listener;

import me.diademiemi.celestialmovement.CelestialMovement;
import me.diademiemi.celestialmovement.persistentdata.PlayerPreferences;
import me.diademiemi.celestialmovement.persistentdata.PreferenceList;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class DashListener implements Listener { // Change "BlockBreakEventListener" to the name of your listener

    /**
     * Constructor
     */
    public DashListener() {
        // Constructor
    }

    public static HashMap<UUID, Integer> dashes = new HashMap<UUID, Integer>();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
            PlayerPreferences prefs = PreferenceList.getPlayerPreferences(p);
            if (prefs.getDash()) {
                e.setCancelled(true);
                p.setFlying(false);
                if (dashes.containsKey(p.getUniqueId()) && dashes.get(p.getUniqueId()) > 0) {
                    if (prefs.getDoubleDash() || prefs.getUnlimitedDash()) {
                        // Unlimited double dash
                        if (!prefs.getUnlimitedDash()) {
                            if (dashes.get(p.getUniqueId()) == 2) {
                                p.setAllowFlight(false);
                                p.setFlying(false);
                                return;
                            }
                        }
                        dashes.put(p.getUniqueId(), dashes.get(p.getUniqueId()) + 1);
                    }
                } else {
                    dashes.put(p.getUniqueId(), 1);
                    if (!prefs.getDoubleDash() && !prefs.getUnlimitedDash()) {
                        p.setAllowFlight(false);
                    }
                }

                // Get the block below the player
                Block b = p.getLocation().getBlock().getRelative(0, -2, 0);
                // Check if block is solid
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1, 1);
                p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation(), 40, 0, 0, 0, 0.5);

                if (b.getType().isSolid() && p.isSneaking()) {
                    // Do a "hyperdash" if the player is sneaking and close to the ground
                    p.setVelocity(p.getLocation().getDirection().multiply(1.3).setY(0.2));
                } else {
                    // Normal dash, should stop midair
                    p.setVelocity(p.getLocation().getDirection().multiply(10));
                    BukkitScheduler scheduler = CelestialMovement.getPlugin().getServer().getScheduler();

                    scheduler.runTaskLater(CelestialMovement.getPlugin(), () -> {
                        p.setVelocity(new Vector(0.15, 0.15, 0.15));
                    }, 2L); // Reset velocity after 2 ticks
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getUniqueId() != null) {
            // Reset when player touches the ground
            if (p.isOnGround()) {
                dashes.remove(p.getUniqueId());
                PlayerPreferences prefs = PreferenceList.getPlayerPreferences(p);
                if (prefs.getDash()) {
                    p.setAllowFlight(true);
                }
                if (PreferenceList.getPlayerPreferences(p).getDash()) {
                    p.setAllowFlight(true);
                }
            }
            // Reset fall damage if a player has dashed
            if (dashes.containsKey(p.getUniqueId())) {
                if (dashes.get(p.getUniqueId()) > 0) {
                    p.setFallDistance(0);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (dashes.containsKey(p.getUniqueId())) {
            dashes.remove(p.getUniqueId());
        }
    }

}
