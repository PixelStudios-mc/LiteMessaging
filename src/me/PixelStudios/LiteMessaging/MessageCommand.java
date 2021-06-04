package me.PixelStudios.LiteMessaging;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    Main plugin;

    public MessageCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("message") || label.equalsIgnoreCase("msg")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("not-a-player")));
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 0) {
                return false;
            }
            if(args.length == 1) {
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("not-a-player")));
                return true;
            }
            if(target.getName() == player.getName()) {
                player.sendMessage(ChatColor.RED + "You can't message yourself.");
                return true;
            }



            StringBuilder preMessage = new StringBuilder();
            for(int i = 1; i < args.length; i++) {
                preMessage.append(args[i]).append(" ");
            }
            String message = preMessage.toString().trim();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message-color")) + "To " + target.getDisplayName() + ": " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message-input-color") + message));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message-color")) + "From " + player.getDisplayName() + ": " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message-input-color") + message));
            String actionMessage = plugin.getConfig().getString("action-bar-message-color") + "From " + player.getDisplayName() + ": " + plugin.getConfig().getString("action-bar-input-color") + message;
            if(plugin.getConfig().getBoolean("action-bar")) {
                target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionMessage));
            }
            if(plugin.getConfig().getBoolean("enable-sounds")) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            Main.hashMap.put(player.getUniqueId(), target.getUniqueId());
            Main.hashMap.put(target.getUniqueId(), player.getUniqueId());
            return true;

        }
        return true;
    }
}
