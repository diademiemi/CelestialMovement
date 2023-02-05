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
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class MovementListener implements Listener { // Change "BlockBreakEventListener" to the name of your listener

    /**
     * Constructor
     */
    public MovementListener() {
        // Constructor
    }

    public static HashMap<UUID, Integer> dashes = new HashMap<>();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
            PlayerPreferences prefs = PreferenceList.getPlayerPreferences(p);
            // Check if player is touching a wall
            if (p.getLocation().add(0.7, 0, 0).getBlock().getType().isSolid() ||
                    p.getLocation().add(-0.7, 0, 0).getBlock().getType().isSolid() ||
                    p.getLocation().add(0, 0, 0.7).getBlock().getType().isSolid() ||
                    p.getLocation().add(0, 0, -0.7).getBlock().getType().isSolid()) {
                // Check if player has wall jump enabled, if not, check if they have dash enabled
                if (prefs.getWallJump()) {
                    e.setCancelled(true);
                    p.setFlying(false);
                    p.setVelocity(p.getLocation().getDirection().multiply(0.75).add(new Vector(0,0.4,0)));
                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GRASS_STEP, 0.5f, 2f);
                    return; // Don't continue for dash
                }
            }

            // Midair dash
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
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.2f, 0.7f);
                p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 20, 0, 0, 0, 0.1);

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
        // Check if player is in survival or adventure mode
        if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {

            if (p.getUniqueId() != null) {
                // Reset dash when player touches the ground
                PlayerPreferences prefs = PreferenceList.getPlayerPreferences(p);

                if (p.isOnGround()) {
                    dashes.remove(p.getUniqueId());
                    if (prefs.getDash()) {
                        p.setAllowFlight(true);
                    }
                }
                // Reset fall damage if a player has dashed
                if (dashes.containsKey(p.getUniqueId())) {
                    if (dashes.get(p.getUniqueId()) > 0) {
                        p.setFallDistance(0);
                    }
                }

                // Check if the player is in the air
                if (!p.isOnGround()) {
                    // Check if player is touching a wall
                    if (p.getLocation().add(0.5, 0, 0).getBlock().getType().isSolid() ||
                        p.getLocation().add(-0.5, 0, 0).getBlock().getType().isSolid() ||
                        p.getLocation().add(0, 0, 0.5).getBlock().getType().isSolid() ||
                        p.getLocation().add(0, 0, -0.5).getBlock().getType().isSolid()) {


                        // Check if player is sneaking
                        if (p.isSneaking()) {
                            if (prefs.getWallClimb()) {
                                // Check if player is looking up
                                if (p.getLocation().getPitch() < -20) {
                                    // Slowly move the player up
                                    p.setVelocity(p.getVelocity().setY(0.15));
                                } else {
                                    // Slowly move the player down
                                    p.setVelocity(p.getVelocity().setY(-0.01));
                                }
                            }

                        }
                    }
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
