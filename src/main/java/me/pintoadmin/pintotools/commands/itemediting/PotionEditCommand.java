package me.pintoadmin.pintotools.commands.itemediting;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.*;

public class PotionEditCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof org.bukkit.entity.Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length < 1) {
            player.sendMessage("Usage: /potionedit <clear|add|remove> <effect> [duration] [amplifier]");
            return true;
        }
        if(!player.getInventory().getItemInMainHand().getType().toString().contains("POTION") && !player.getInventory().getItemInMainHand().getType().toString().contains("TIPPED")) {
            player.sendMessage("You must be holding a potion in your main hand.");
            return true;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        if(meta == null) {
            player.sendMessage("Error: Potion meta is null.");
            return true;
        }
        String action = args[0].toLowerCase();
        switch(action){
            case "clear":
                meta.clearCustomEffects();
                item.setItemMeta(meta);
                player.sendMessage("Cleared all custom effects from the potion.");
                break;
            case "add":
                if(args.length < 2) {
                    player.sendMessage("Usage: /potionedit add <effect> [duration] [amplifier]");
                    return true;
                }
                String effectName = args[1].toUpperCase();
                PotionEffectType effectType = PotionEffectType.getByName(effectName);
                if(effectType == null) {
                    player.sendMessage(ChatColor.RED+"Invalid effect type: " + effectName);
                    return true;
                }
                int duration = 600; // default 30 seconds
                int amplifier = 0; // default level 1
                if(args.length >= 3) {
                    try {
                        duration = Integer.parseInt(args[2]) * 20; // convert seconds to ticks
                    } catch(NumberFormatException e) {
                        player.sendMessage(ChatColor.RED+"Invalid duration: " + args[2]);
                        return true;
                    }
                }
                if(args.length >= 4) {
                    try {
                        amplifier = Integer.parseInt(args[3]);
                    } catch(NumberFormatException e) {
                        player.sendMessage(ChatColor.RED+"Invalid amplifier: " + args[3]);
                        return true;
                    }
                }
                PotionEffect newEffect = new PotionEffect(effectType, duration, amplifier);
                meta.addCustomEffect(newEffect, true);
                item.setItemMeta(meta);
                player.sendMessage("Added effect: " + effectType.getName() + " Duration: " + (duration / 20) + "s Amplifier: " + (amplifier + 1));
                break;
            case "remove":
                if(args.length < 2) {
                    player.sendMessage("Usage: /potionedit remove <effect>");
                    return true;
                }
                effectName = args[1].toUpperCase();
                effectType = PotionEffectType.getByName(effectName);
                if(effectType == null) {
                    player.sendMessage(ChatColor.RED+"Invalid effect type: " + effectName);
                    return true;
                }
                if(meta.hasCustomEffect(effectType)) {
                    meta.removeCustomEffect(effectType);
                    item.setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN+"Removed effect: " + effectType.getName());
                    return true;
                }
            case "color":
                if(args.length < 2) {
                    player.sendMessage("Usage: /potionedit color <hexcolor>");
                    return true;
                }
                String colorHex = args[1];
                if(colorHex.length() != 7 || !colorHex.startsWith("#")) {
                    player.sendMessage(ChatColor.RED+"Invalid color format. Use hex format like #RRGGBB.");
                    return true;
                }
                try {
                    Color color = Color.fromRGB(
                            Integer.valueOf(colorHex.substring(1, 3), 16),
                            Integer.valueOf(colorHex.substring(3, 5), 16),
                            Integer.valueOf(colorHex.substring(5, 7), 16)
                    );
                    meta.setColor(Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()));
                    item.setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN+"Set potion color to: " + colorHex);
                } catch(NumberFormatException e) {
                    player.sendMessage(ChatColor.RED+"Invalid color hex: " + colorHex);
                }
                break;
        }
        return true;
    }
}
