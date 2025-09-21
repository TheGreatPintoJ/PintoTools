package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.*;
import org.bukkit.command.*;

public class SudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2) {
            sender.sendMessage("Usage: /sudo <player> <command>");
            return false;
        }
        String playerName = args[0];
        String commandToExecute = String.join(" ", args).substring(playerName.length() + 1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + playerName + " run " + commandToExecute);
        return true;
    }
}
