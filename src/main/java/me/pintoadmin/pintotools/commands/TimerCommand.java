package me.pintoadmin.pintotools.commands;

import org.bukkit.command.*;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1){
            sender.sendMessage("Usage: /timer <seconds>");
            return true;
        }
        try {
            int seconds = Integer.parseInt(args[0]);
            if(seconds <= 0){
                sender.sendMessage("Please provide a positive number of seconds.");
                return true;
            }
            sender.sendMessage("Timer started for " + seconds + " seconds.");
            new Thread(() -> {
                try {
                    Thread.sleep(seconds * 1000L);
                    sender.sendMessage("Timer finished!");
                } catch (InterruptedException e) {
                    sender.sendMessage("Timer was interrupted.");
                }
            }).start();
        } catch (NumberFormatException e) {
            sender.sendMessage("Please provide a valid number of seconds.");
        }
        return true;
    }
}
