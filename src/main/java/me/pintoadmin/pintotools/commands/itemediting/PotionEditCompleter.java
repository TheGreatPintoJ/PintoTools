package me.pintoadmin.pintotools.commands.itemediting;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.*;

import java.util.*;

public class PotionEditCompleter implements TabCompleter {

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)) {
            return java.util.Collections.emptyList();
        }
        Player player = (Player) sender;

        if(player.getInventory().getItemInMainHand().getType() == org.bukkit.Material.AIR || !player.getInventory().getItemInMainHand().getType().toString().contains("POTION")) {
            return List.of("You're not holding a potion" );
        }

        if(args.length == 1) {
            return java.util.Arrays.asList("clear", "add", "remove", "color").stream()
                    .filter(option -> option.startsWith(args[0].toLowerCase()))
                    .toList();
        } else if(args.length == 2 && args[0].equalsIgnoreCase("add")) {
            java.util.List<String> effects = new java.util.ArrayList<>();
            for(org.bukkit.potion.PotionEffectType type : org.bukkit.potion.PotionEffectType.values()) {
                if(type != null) {
                    effects.add(type.getName().toLowerCase());
                }
            }
            return effects;
        } else if(args.length == 2 && args[0].equalsIgnoreCase("remove")){
            List<String> currentEffects = new ArrayList<>();
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item.getItemMeta() instanceof PotionMeta meta) {
                for(PotionEffect effect : meta.getCustomEffects()) {
                    currentEffects.add(effect.getType().getName().toLowerCase());
                }
            }
            return currentEffects;
        } else if(args.length == 3 && args[0].equalsIgnoreCase("add")) {
            return java.util.Collections.singletonList("<duration in seconds>");
        } else if(args.length == 4 && args[0].equalsIgnoreCase("add")) {
            return java.util.Collections.singletonList("<amplifier level>");
        }
        return java.util.Collections.emptyList();
    }
}
