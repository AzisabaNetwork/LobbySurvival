package net.azisaba.lobby.lobbysurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class MessageListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld().getName().contains("survival")) {
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "荒らし対策のため、ロビーサバイバルはほぼ毎日リセットされます。\n"
                    + ChatColor.DARK_RED + "大事なアイテムは " + ChatColor.YELLOW + "/ec " + ChatColor.DARK_RED + "コマンドで保管できます。");
        }
    }
}
