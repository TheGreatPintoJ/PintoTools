package me.pintoadmin.pintotools.commands;

import org.bukkit.command.*;

public class SummonNoAICompleter implements TabCompleter {
    @Override
    public java.util.List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1){
            java.util.List<String> entityTypes = new java.util.ArrayList<>();
            for(org.bukkit.entity.EntityType entityType : org.bukkit.entity.EntityType.values()){
                entityTypes.add(entityType.name().toLowerCase());
            }
            return entityTypes;
        } else if(strings.length == 2){
            return java.util.List.of("true", "false");
        }
        return java.util.List.of();
    }
}
