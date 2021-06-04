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
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("message") || label.equalsIgnoreCase("msg")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Supply a player. Here's the usage");
                return false;
            }
            if(args.length == 1) {
                sender.sendMessage(ChatColor.RED + "You need a message. Here's the usage");
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                sender.sendMessage(ChatColor.RED + "Not a valid player.");
                return true;
            }
            if(target.getName() == player.getName()) {
                player.sendMessage(ChatColor.GREEN + "Oh look at you so lonely... you can talk to yourself " + ChatColor.RED + "" + ChatColor.BOLD + "NOPE");
                return true;
            }
            StringBuilder preMessage = new StringBuilder();
            for(int i = 1; i < args.length; i++) {
                preMessage.append(args[i]).append(" ");
            }
            String message = preMessage.toString().trim();
            player.sendMessage(ChatColor.AQUA + "To " + target.getDisplayName() + ": " + message);
            target.sendMessage(ChatColor.AQUA + "From " + player.getDisplayName() + ": " + message);
            String actionMessage = "§bFrom " + player.getDisplayName() + ": " + message;
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionMessage));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1f,1f);
            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1f,1f);
            Main.hashMap.put(player.getUniqueId(), target.getUniqueId());
            Main.hashMap.put(target.getUniqueId(), player.getUniqueId());
            return true;

        }
        return true;
    }
}
