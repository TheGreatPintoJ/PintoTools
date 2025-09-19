package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        if(args.length == 0) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            player.setHealth(player.getMaxHealth());
            player.sendMessage(ChatColor.GREEN+"You have been healed.");
        } else if(args.length == 1 && Bukkit.getPlayer(args[0]) != null) {
            Player target = (Player) sender;
            target.setHealth(target.getMaxHealth());
            target.sendMessage(ChatColor.GREEN+"You have been healed.");
            sender.sendMessage(ChatColor.GREEN+"Healed " + target.getName() + ".");
        } else {
            sender.sendMessage(ChatColor.RED+"Usage: /heal [player]");
        }
        return true;
    }
}
