package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        if(args.length == 0) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.GREEN+"You have been fed.");
        } else if(args.length == 1 && Bukkit.getPlayer(args[0]) != null) {
            Player target = Bukkit.getPlayer(args[0]);
            target.setFoodLevel(20);
            target.sendMessage(ChatColor.GREEN+"You have been fed.");
            sender.sendMessage(ChatColor.GREEN+"Fed " + target.getName() + ".");
        } else {
            sender.sendMessage(ChatColor.RED+"Usage: /feed [player]");
        }
        return true;
    }
}
