package me.pintoadmin.pintotools.commands;

import me.pintoadmin.pintotools.*;
import org.bukkit.command.*;

import java.util.*;

public class WorldRuleCompleter implements TabCompleter {
    private final PintoTools pintoTools;

    public WorldRuleCompleter(PintoTools plugin) {
        this.pintoTools = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 3) {
            for (org.bukkit.World world : pintoTools.getServer().getWorlds()) {
                completions.add(world.getName());
            }
        } else if (args.length == 1) {
            completions.add("allowPVP");
            completions.add("doDaylightCycle");
            completions.add("doEntityDrops");
            completions.add("doFireTick");
            completions.add("doMobLoot");
            completions.add("doMobSpawning");
            completions.add("doTileDrops");
            completions.add("keepInventory");
            completions.add("mobGriefing");
            completions.add("naturalRegeneration");
            completions.add("randomTickSpeed");
            completions.add("sendCommandFeedback");
            completions.add("showDeathMessages");
            completions.add("spectatorsGenerateChunks");
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("randomTickSpeed")) {
                completions.add("0");
                completions.add("1");
                completions.add("2");
                completions.add("3");
                completions.add("4");
                completions.add("5");
                completions.add("10");
                completions.add("15");
                completions.add("20");
                completions.add("25");
                completions.add("30");
                completions.add("40");
                completions.add("50");
                completions.add("100");
            } else {
                completions.add("true");
                completions.add("false");
            }
        }
        return completions.stream().filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }
}
