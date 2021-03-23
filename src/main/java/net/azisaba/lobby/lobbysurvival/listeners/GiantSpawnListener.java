package net.azisaba.lobby.lobbysurvival.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GiantSpawnListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Location loc = e.getEntity().getLocation();
        if (!loc.getWorld().getName().contains("survival")) {
            return;
        }

        loc.getWorld().spawnEntity(loc, EntityType.GIANT);
    }
}
