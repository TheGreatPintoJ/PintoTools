package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.command.*;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
        if(args.length == 0) {
            boolean newFlightState = !player.getAllowFlight();
            player.setAllowFlight(newFlightState);
            player.setFlying(newFlightState);
            player.sendMessage(org.bukkit.ChatColor.GREEN + "Flight mode " + (newFlightState ? "enabled" : "disabled") + ".");
        } else if(args.length == 1 && org.bukkit.Bukkit.getPlayer(args[0]) != null) {
            org.bukkit.entity.Player target = org.bukkit.Bukkit.getPlayer(args[0]);
            boolean newFlightState = !target.getAllowFlight();
            target.setAllowFlight(newFlightState);
            target.setFlying(newFlightState);
            target.sendMessage(org.bukkit.ChatColor.GREEN + "Your flight mode has been " + (newFlightState ? "enabled" : "disabled") + " by " + player.getName() + ".");
            player.sendMessage(org.bukkit.ChatColor.GREEN + "Flight mode for " + target.getName() + " has been " + (newFlightState ? "enabled" : "disabled") + ".");
        } else {
            sender.sendMessage(org.bukkit.ChatColor.RED + "Usage: /fly [player]");
        }
        return true;
    }
}
