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

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("reply") || label.equalsIgnoreCase("r") || label.equalsIgnoreCase("re")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!Main.hashMap.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Look's like you haven't sent a message yet, that means you can't reply");
                    return true;

                }
                UUID uuid = Main.hashMap.get(player.getUniqueId());


                if (uuid == null) {
                    player.sendMessage(ChatColor.RED + "Oops, there has been an error, try again.");
                    return true;
                }


                Player res = Bukkit.getPlayer(uuid);
                if (res == null) {
                    player.sendMessage(ChatColor.RED + "Looks like the player is not online");
                    return true;
                }
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Whoops, you need a message. Here's the usage");
                    return false;
                }
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }

                String actionMessage = "§bFrom " + player.getDisplayName() + ": " + message;
                player.sendMessage(ChatColor.AQUA + "To " + res.getDisplayName() + ": " + message);
                res.sendMessage(ChatColor.AQUA + "From " + player.getDisplayName() + ": " + message);
                res.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionMessage));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                res.playSound(res.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }
        return true;
    }
}
