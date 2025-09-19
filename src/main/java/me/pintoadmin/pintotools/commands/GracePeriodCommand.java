package me.pintoadmin.pintotools.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class GracePeriodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length != 1){
            sender.sendMessage("Usage: /graceperiod <seconds>");
            return true;
        }
        try {
            int seconds = Integer.parseInt(args[0]);
            if(seconds <= 0){
                sender.sendMessage("Please provide a positive number of seconds.");
                return true;
            }
            sender.sendMessage("Grace period started for " + seconds + " seconds.");
            new Thread(() -> {
                try {
                    player.getWorld().setPVP(false);
                    Thread.sleep(seconds * 1000L);
                    for(Player p : player.getWorld().getPlayers()){
                        p.sendMessage("Grace Period finished!");
                    }
                    player.getWorld().setPVP(true);
                } catch (InterruptedException e) {
                    sender.sendMessage("Grace Period was interrupted.");
                }
            }).start();
        } catch (NumberFormatException e) {
            sender.sendMessage("Please provide a valid number of seconds.");
        }
        return true;
    }
}
