package me.PixelStudios.LiteMessaging;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {


    public static HashMap<UUID, UUID> hashMap = new HashMap<>();
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Metrics metrics = new Metrics(this, 11575);
        this.getCommand("message").setExecutor(new MessageCommand(this));
        this.getCommand("reply").setExecutor(new ReplyCommand(this));
        this.getCommand("litemessages").setExecutor(new LiteMessagesCommand(this));

    }

    @Override
    public void onDisable() {
        //
    }


}
