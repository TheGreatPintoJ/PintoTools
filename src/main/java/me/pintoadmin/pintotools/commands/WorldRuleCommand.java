package me.pintoadmin.pintotools.commands;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class WorldRuleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length < 2) {
            player.sendMessage("Usage: /worldrule <gamerule> <value> [world]");
            return true;
        }
        String gameruleName = args[0];
        String value = args[1];
        World world = player.getWorld();
        if(args.length == 3){
            world = Bukkit.getWorld(args[2]);
            if(world == null){
                player.sendMessage("World " + args[2] + " not found.");
                return true;
            }
        }
        switch (gameruleName){
            case "allowPVP":
                world.setPVP(Boolean.parseBoolean(value));
                player.sendMessage("Set allowPVP to " + value + " in world " + world.getName());
                break;
            default:
                if(GameRule.getByName(gameruleName) == null) {
                    player.sendMessage("Invalid gamerule: " + gameruleName);
                    return true;
                } else {
                    switch (gameruleName){
                        case "doDaylightCycle", "doWeatherCycle" -> {
                            if(!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                                player.sendMessage("Invalid value for " + gameruleName + ". Use true or false.");
                                return true;
                            }
                        }
                        case "randomTickSpeed" -> {
                            try {
                                int intValue = Integer.parseInt(value);
                                if(intValue < 0) {
                                    player.sendMessage("Invalid value for randomTickSpeed. Must be a non-negative integer.");
                                    return true;
                                }
                            } catch(NumberFormatException e) {
                                player.sendMessage("Invalid value for randomTickSpeed. Must be a non-negative integer.");
                                return true;
                            }
                        }
                        case "maxEntityCramming" -> {
                            try {
                                int intValue = Integer.parseInt(value);
                                if(intValue < 0 || intValue > 24) {
                                    player.sendMessage("Invalid value for maxEntityCramming. Must be between 0 and 24.");
                                    return true;
                                }
                            } catch(NumberFormatException e) {
                                player.sendMessage("Invalid value for maxEntityCramming. Must be between 0 and 24.");
                                return true;
                            }
                        }
                        case "mobGriefing" -> {
                            if(!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                                player.sendMessage("Invalid value for mobGriefing. Use true or false.");
                                return true;
                            }
                        }
                    }
                    player.sendMessage("Set gamerule " + gameruleName + " to " + value + " in world " + world.getName());
                }
        }
        return true;
    }
}
