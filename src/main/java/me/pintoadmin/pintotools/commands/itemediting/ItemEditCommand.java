package me.pintoadmin.pintotools.commands.itemediting;

import com.google.common.collect.*;
import org.bukkit.*;
import org.bukkit.attribute.*;
import org.bukkit.command.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class ItemEditCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length < 2) {
            sender.sendMessage(org.bukkit.ChatColor.RED + "Usage: /itemedit <operation> <value>");
            return true;
        }
        if(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().isAir()) {
            sender.sendMessage(org.bukkit.ChatColor.RED + "You must be holding an item to edit.");
            return true;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        switch (args[0].toLowerCase()) {
            case "name":
                String newName = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&', newName));
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item name changed to: " + newName);
                break;
            case "lore":
                String newLore = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
                ItemStack loreItem = player.getInventory().getItemInMainHand();
                ItemMeta loreMeta = loreItem.getItemMeta();
                List<String> loreList = loreMeta.hasLore() ? loreMeta.getLore() : new java.util.ArrayList<>();
                loreList.add(org.bukkit.ChatColor.translateAlternateColorCodes('&', newLore));
                loreMeta.setLore(loreList);
                loreItem.setItemMeta(loreMeta);
                player.getInventory().setItemInMainHand(loreItem);
                sender.sendMessage(org.bukkit.ChatColor.GREEN + "Added lore: " + newLore);
                break;
            case "attribute":
                if(player.getInventory().getItemInMainHand().getItemMeta() == null) {
                    player.sendMessage("This item has no metadata.");
                    return true;
                }
                ItemMeta itemMeta = item.getItemMeta();

                Attribute attribute = Attribute.valueOf(args[1].toUpperCase());
                AttributeModifier modifier;
                if(args.length == 4){
                    if(EquipmentSlotGroup.getByName(args[3].toLowerCase()) == null){
                        sender.sendMessage("Invalid slot.");
                        return true;
                    }
                    modifier = new AttributeModifier(UUID.randomUUID(),"custom",
                            Double.parseDouble(args[2]), AttributeModifier.Operation.ADD_NUMBER,
                            EquipmentSlotGroup.getByName(args[3].toLowerCase()));
                } else if(args.length == 3){
                    modifier = new AttributeModifier("custom", Double.parseDouble(args[2]), AttributeModifier.Operation.ADD_NUMBER);
                } else {
                    sender.sendMessage("Usage: /itemattribute <attribute> <value> [slot]");
                    return true;
                }
                Multimap<Attribute, AttributeModifier> attributes = ArrayListMultimap.create();
                if(itemMeta.getAttributeModifiers() != null){
                    attributes.putAll(itemMeta.getAttributeModifiers());
                }
                attributes.put(attribute, modifier);
                itemMeta.setAttributeModifiers(attributes);
                item.setItemMeta(itemMeta);
                player.getInventory().setItemInMainHand(item);
                player.sendMessage("Attribute " + args[1] + " set to " + args[2] + " on the item in your main hand.");
                break;
            case "amount":
                if(args.length == 1){
                    player.getInventory().getItemInMainHand().setAmount(127);
                } else if(args.length == 2){
                    player.getInventory().getItemInMainHand().setAmount(Integer.parseInt(args[1]));
                }
                return true;
            case "flags":
                if(item.getItemMeta() == null) {
                    player.sendMessage("This item has no metadata.");
                    return true;
                }
                ItemMeta flagsMeta = item.getItemMeta();
                if(args[1].equalsIgnoreCase("unbreakable")){
                    boolean unbreakable = !flagsMeta.isUnbreakable();
                    flagsMeta.setUnbreakable(unbreakable);
                    sender.sendMessage(org.bukkit.ChatColor.GREEN + "Set item unbreakable to: " + unbreakable);
                    item.setItemMeta(flagsMeta);
                    player.getInventory().setItemInMainHand(item);
                    return true;
                }
                if(flagsMeta.hasItemFlag(ItemFlag.valueOf(args[1].toUpperCase()))){
                    flagsMeta.removeItemFlags(ItemFlag.valueOf(args[1].toUpperCase()));
                    sender.sendMessage(org.bukkit.ChatColor.GREEN + "Removed item flag: " + args[1].toUpperCase());
                } else {
                    flagsMeta.addItemFlags(ItemFlag.valueOf(args[1].toUpperCase()));
                    sender.sendMessage(org.bukkit.ChatColor.GREEN + "Added item flag: " + args[1].toUpperCase());
                }
                item.setItemMeta(flagsMeta);
                player.getInventory().setItemInMainHand(item);
                break;
            case "enchant":
                if(player.getInventory().getItemInMainHand().getType().isAir()){
                    player.sendMessage(ChatColor.RED+"Hold an item to enchant");
                    return true;
                }
                Enchantment targetEnchant;
                try {
                    targetEnchant = Enchantment.getByName(args[1].toUpperCase());
                    player.getInventory().getItemInMainHand().addUnsafeEnchantment(targetEnchant, Integer.parseInt(args[2]));
                    player.sendMessage(ChatColor.GREEN+"Enchanted "+player.getInventory().getItemInMainHand().getType()+" with "+args[1]);
                } catch (RuntimeException e){
                    player.sendMessage(ChatColor.RED+"Could not find an enchantment called "+args[0]);
                }
                break;
            case "clear":
                switch (args[1].toLowerCase()) {
                    case "name":
                        ItemMeta clearNameMeta = item.getItemMeta();
                        clearNameMeta.setDisplayName(null);
                        item.setItemMeta(clearNameMeta);
                        sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item name cleared.");
                        break;
                    case "lore":
                        ItemMeta clearLoreMeta = item.getItemMeta();
                        clearLoreMeta.setLore(null);
                        item.setItemMeta(clearLoreMeta);
                        sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item lore cleared.");
                        break;
                    case "attributes":
                        if (item.getItemMeta() == null) {
                            player.sendMessage("This item has no metadata.");
                            return true;
                        }
                        ItemMeta clearAttrMeta = item.getItemMeta();
                        clearAttrMeta.setAttributeModifiers(null);
                        item.setItemMeta(clearAttrMeta);
                        sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item attributes cleared.");
                        break;
                    case "enchantments":
                        ItemStack clearEnchantItem = player.getInventory().getItemInMainHand();
                        Map<org.bukkit.enchantments.Enchantment, Integer> enchantments = clearEnchantItem.getEnchantments();
                        for (org.bukkit.enchantments.Enchantment enchantment : enchantments.keySet()) {
                            clearEnchantItem.removeEnchantment(enchantment);
                        }
                        player.getInventory().setItemInMainHand(clearEnchantItem);
                        sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item enchantments cleared.");
                        break;
                    case "flags":
                        if (item.getItemMeta() == null) {
                            player.sendMessage("This item has no metadata.");
                            return true;
                        }
                        ItemMeta clearFlagsMeta = item.getItemMeta();
                        for (org.bukkit.inventory.ItemFlag flag : org.bukkit.inventory.ItemFlag.values()) {
                            clearFlagsMeta.removeItemFlags(flag);
                        }
                        item.setItemMeta(clearFlagsMeta);
                        sender.sendMessage(org.bukkit.ChatColor.GREEN + "Item flags cleared.");
                        break;
                    case "all":
                        ItemStack newItem = new ItemStack(item.getType(), item.getAmount());
                        player.getInventory().setItemInMainHand(newItem);
                        break;
                    default:
                        sender.sendMessage(org.bukkit.ChatColor.RED + "Unknown clear operation. Available: name, lore, attributes, enchantments, flags, all.");
                        break;
                }
                break;
            default:
                sender.sendMessage(org.bukkit.ChatColor.RED + "Unknown operation. Available operations: name, lore, attribute, amount, clear.");
                break;
        }

        return true;
    }
}
