package net.azisaba.lobby.lobbysurvival;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import net.azisaba.lobby.lobbysurvival.listeners.GiantSpawnListener;
import net.azisaba.lobby.lobbysurvival.listeners.ItemDropListener;
import net.azisaba.lobby.lobbysurvival.listeners.MessageListener;
import net.azisaba.lobby.lobbysurvival.listeners.PreventGriefListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public class LobbySurvival extends JavaPlugin implements EventListener {

    private static TaskChainFactory taskChainFactory;

    @Override
    public void onEnable() {
        taskChainFactory = BukkitTaskChainFactory.create(this);

        Bukkit.getPluginManager().registerEvents(new GiantSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(), this);
        Bukkit.getPluginManager().registerEvents(new MessageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PreventGriefListener(), this);

        Bukkit.getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(getName() + " disabled.");
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }
}
