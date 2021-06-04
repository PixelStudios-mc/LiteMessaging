package me.PixelStudios.LiteMessaging;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LiteMessagesCommand implements CommandExecutor {
    Main plugin;

    public LiteMessagesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("litemessages") || label.equalsIgnoreCase("lm")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("not-a-player")));
                return true;
            }
            Player player = (Player) sender;

            if (args.length == 0) {
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("lm.reload")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("required-permission")));
                    return true;
                }
                plugin.reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload-message")));
                return true;
            }

            return false;
        }
        return true;
    }
}
