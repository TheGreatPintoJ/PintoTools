package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.command.*;

public class InvSeeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1){
            sender.sendMessage("Usage: /invsee <player>");
            return true;
        }
        if(!(sender instanceof org.bukkit.entity.Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
        org.bukkit.entity.Player target = org.bukkit.Bukkit.getPlayerExact(args[0]);
        if(target == null || !target.isOnline()){
            player.sendMessage("Player not found or not online.");
            return true;
        }
        player.openInventory(target.getInventory());
        player.sendMessage("Opened inventory of " + target.getName());
        return true;
    }
}
