package net.azisaba.lobby.lobbysurvival.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreventGriefListener implements Listener {

    private final List<Material> preventPlaceMaterials = Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.COMMAND, Material.COMMAND_CHAIN, Material.COMMAND_REPEATING);
    private final List<Material> preventRightClickMaterials = Collections.singletonList(Material.COMMAND_MINECART);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Material mat = e.getBlockPlaced().getType();
        if (preventPlaceMaterials.contains(mat)) {
            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (e.getItem() == null) {
            return;
        }

        if (preventRightClickMaterials.contains(e.getItem().getType())) {
            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }
}
