package me.pintoadmin.pintotools;

import me.pintoadmin.pintotools.commands.*;
import me.pintoadmin.pintotools.commands.itemediting.*;
import me.pintoadmin.pintotools.commands.playerbased.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class PintoTools extends JavaPlugin {

    public static Plugin getPlugin() {
        return JavaPlugin.getPlugin(PintoTools.class);
    }

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());

        getCommand("fly").setExecutor(new FlyCommand());

        getCommand("itemedit").setExecutor(new ItemEditCommand());
        getCommand("itemedit").setTabCompleter(new ItemEditCompleter());

        getCommand("potionedit").setExecutor(new PotionEditCommand());
        getCommand("potionedit").setTabCompleter(new PotionEditCompleter());

        getCommand("summonnoai").setExecutor(new SummonNoAICommand());
        getCommand("summonnoai").setTabCompleter(new SummonNoAICompleter());

        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("timer").setTabCompleter(new NumberCompleter());
        getCommand("graceperiod").setExecutor(new GracePeriodCommand());
        getCommand("graceperiod").setTabCompleter(new NumberCompleter());

        getCommand("invsee").setExecutor(new InvSeeCommand());

        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("sudo").setTabCompleter(new SudoCompleter());

        getCommand("worldrule").setExecutor(new WorldRuleCommand());
        getCommand("worldrule").setTabCompleter(new WorldRuleCompleter(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
