package me.pintoadmin.pintotools.commands.itemediting;

import org.bukkit.attribute.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

public class ItemEditCompleter implements TabCompleter {
    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)){
            return java.util.List.of();
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            return java.util.Arrays.asList("name", "lore", "attribute", "clear", "amount", "enchant", "damage", "flags").stream()
                    .filter(option -> option.startsWith(args[0].toLowerCase()))
                    .toList();
        }
        switch (args[0].toLowerCase()) {
            case "name":
                return java.util.List.of("<new name>");
            case "lore":
                return java.util.List.of("<new lore line>");
            case "attribute":
                if(args.length == 2){
                    return Arrays.stream(Attribute.values())
                        .map(attribute -> attribute.getKey().toString().replace("minecraft:", ""))
                        .filter(attr -> attr.startsWith(args[1].toLowerCase()))
                        .toList();
                } else if(args.length == 4){
                    List<String> slots = List.of("any","armor","head", "chest", "legs", "feet","hand", "mainhand", "offhand");
                    return slots.stream().filter(slot -> slot.startsWith(args[3].toLowerCase())).toList();
                }
                return List.of();
            case "amount":
                return java.util.List.of("<new amount>");
            case "flags":
                List<String> flags = new ArrayList<>();
                for(ItemFlag flag : ItemFlag.values()){
                    flags.add(flag.name().toLowerCase());
                }
                flags.add("unbreakable");
                return flags.stream().filter(flag -> flag.startsWith(args[1].toLowerCase())).toList();
            case "enchant":
                if(args.length == 2){
                    List<String> enchantments = new ArrayList<>();
                    for(org.bukkit.enchantments.Enchantment enchantment : org.bukkit.enchantments.Enchantment.values()){
                        enchantments.add(enchantment.getTranslationKey()
                                .replace("minecraft.", "")
                                .replace("enchantment.", ""));
                    }
                    return enchantments.stream().filter(enchant -> enchant.startsWith(args[1].toLowerCase())).toList();
                }
                return List.of();
            case "damage":
                int maxDurability = player.getInventory().getItemInMainHand().getType().getMaxDurability();
                return List.of("<new damage>","0",""+maxDurability,""+(maxDurability/2));
            case "clear":
                return java.util.List.of("name", "lore", "attributes", "enchantments", "flags", "all").stream().filter(option -> option.startsWith(args[1].toLowerCase())).toList();
            default:
                return java.util.List.of();
        }
    }
}
