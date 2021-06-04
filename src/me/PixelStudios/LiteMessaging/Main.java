package me.PixelStudios.LiteMessaging;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static HashMap<UUID, UUID> hashMap = new HashMap<>();
    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 11575);
        this.getCommand("message").setExecutor(new MessageCommand());
        this.getCommand("reply").setExecutor(new ReplyCommand());

    }

    @Override
    public void onDisable() {
        //
    }
}
