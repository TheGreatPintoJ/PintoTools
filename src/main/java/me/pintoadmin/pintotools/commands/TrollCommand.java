package me.pintoadmin.pintotools.commands;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

public class TrollCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou must be a player to use this command.");
            return true;
        }
        Player player = (Player) sender;
        Player target;

        if(args.length == 0) {
            sender.sendMessage("§cUsage: /troll <trolltype> <player>");
            return true;
        }
        if(args.length == 1) {
            sender.sendMessage("§cUsage: /troll <trolltype> <player>");
            return true;
        }
        target = player.getServer().getPlayer(args[1]);
        if(target == null) {
            sender.sendMessage(ChatColor.RED+"Player not found.");
            return true;
        }
        switch(args[0].toLowerCase()) {
            case "lightning":
                player.getWorld().strikeLightning(target.getLocation());
                sender.sendMessage(ChatColor.GREEN+"Struck lightning at " + target.getName() + "!");
                break;
            case "launch":
                target.setVelocity(target.getLocation().getDirection().setY(10));
                sender.sendMessage(ChatColor.GREEN+"Launched " + target.getName() + " into the air!");
                break;
            case "freeze":
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 255));
                target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 128));
                sender.sendMessage(ChatColor.GREEN+"Froze " + target.getName() + " in place!");
                break;
            default:
                sender.sendMessage(ChatColor.RED+"Unknown troll type. Available types: lightning, launch, freeze.");
                break;
        }
        return true;
    }
}
