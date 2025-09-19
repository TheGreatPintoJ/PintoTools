package me.pintoadmin.pintotools.commands;

import org.bukkit.command.*;

public class TrollCompleter implements TabCompleter {
    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return java.util.Arrays.asList("lightning", "launch", "freeze");
        }
        if(args.length == 2) {
            java.util.List<String> playerNames = new java.util.ArrayList<>();
            for(org.bukkit.entity.Player player : sender.getServer().getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        }
        return java.util.Collections.emptyList();
    }
}
