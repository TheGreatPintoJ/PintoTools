package me.pintoadmin.pintotools.commands;

import org.bukkit.command.*;

public class NumberCompleter implements TabCompleter {
    @Override
    public java.util.List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1){
            return java.util.Arrays.asList("10", "30", "60", "120", "300");
        }
        return java.util.Collections.emptyList();
    }
}
