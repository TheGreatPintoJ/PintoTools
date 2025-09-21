package me.pintoadmin.pintotools.commands.playerbased;

import org.bukkit.command.*;

import java.util.*;

public class SudoCompleter implements TabCompleter {
    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            java.util.List<String> playerNames = new java.util.ArrayList<>();
            for(org.bukkit.entity.Player player : org.bukkit.Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames.stream().filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase())).toList();
        }
        return List.of();
    }
}
