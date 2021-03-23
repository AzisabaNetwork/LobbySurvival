package net.azisaba.lobby.lobbysurvival.listeners;

import net.azisaba.lobby.lobbysurvival.LobbySurvival;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ItemDropListener implements Listener {

    private final List<Material> materialList = Arrays.asList(Material.values().clone());
    private final Random rand = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.getBlock().getWorld().getName().contains("survival")) {
            return;
        }
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) {
            return;
        }

        for (int i = rand.nextInt(9) + 2; 0 < i; i--) {
            ItemStack item = getRandomItem();
            Item entityItem = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
            entityItem.setTicksLived(4800);
        }

        removeExceedItems(e.getBlock().getWorld());
    }

    private ItemStack getRandomItem() {
        Material material = null;
        while (material == null || !isValidItem(material)) {
            material = materialList.get(rand.nextInt(materialList.size()));
        }
        ItemStack item = new ItemStack(material);
        item.setAmount(rand.nextInt(64) + 1);
        return item;
    }

    private final List<Material> invalids = Arrays.asList(Material.AIR, Material.FIRE, Material.WATER, Material.LAVA);

    private boolean isValidItem(Material material) {
        return !invalids.contains(material);
    }

    private void removeExceedItems(World world) {
        LobbySurvival.newSharedChain("RemoveExceedItems")
                .syncFirst(world::getEntities)
                .asyncLast(input -> {
                    List<Item> items = input.stream()
                            .filter(ent -> ent instanceof Item)
                            .map(ent -> (Item) ent)
                            .sorted(Comparator.comparingInt(Entity::getTicksLived).reversed())
                            .collect(Collectors.toList());

                    for (int i = 0, size = items.size(); i < size - 400; i++) {
                        items.get(i).remove();
                    }
                }).execute();
    }
}
