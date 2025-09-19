package me.pintoadmin.pintotools.commands;

import me.pintoadmin.pintotools.*;
import org.bukkit.attribute.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;

import static org.bukkit.Bukkit.getLogger;

public class SummonNoAICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        if(strings.length == 0 || strings.length > 2){
            commandSender.sendMessage("Usage: /summonnoai <mob> [healing (true/false)]");
            return true;
        }
        Player player = (Player) commandSender;
        try {
            Entity entity = player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(strings[0].toUpperCase()));
            if(entity instanceof LivingEntity){
                ((LivingEntity) entity).setAI(false);
                ((LivingEntity) entity).setRemoveWhenFarAway(false);
            }
            if(strings.length == 2){
                if(strings[1].equalsIgnoreCase("true")){
                    if(entity instanceof LivingEntity livingEntity) {
                        getLogger().info("Summoned entity "+livingEntity.getType().name()+" with no AI and healing");
                        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1024.0);
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                if(livingEntity.isDead() || !livingEntity.isValid()){
                                    this.cancel();
                                    return;
                                }
                                if(livingEntity.getHealth() >= livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()){
                                    return;
                                }
                                livingEntity.setHealth(1024.0);
                            }
                        }.runTaskTimer(PintoTools.getPlugin(), 10L, 1L);
                    }
                }
            }
            player.sendMessage("Summoned "+strings[0]+" with no AI and healing as "+(strings.length == 2 && strings[1].equalsIgnoreCase("true")));
        } catch (IllegalArgumentException e){
            player.sendMessage("Could not find an entity called "+strings[0]);
        } catch (Exception | Error e) {
            player.sendMessage("An error occurred while trying to summon the entity.");
            e.printStackTrace();
        }
        return true;
    }
}
