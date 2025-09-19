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

        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("troll").setTabCompleter(new TrollCompleter());

        getCommand("fly").setExecutor(new FlyCommand());

        getCommand("itemedit").setExecutor(new ItemEditCommand());
        getCommand("itemedit").setTabCompleter(new ItemEditCompleter());

        getCommand("summonnoai").setExecutor(new SummonNoAICommand());
        getCommand("summonnoai").setTabCompleter(new SummonNoAICompleter());

        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("timer").setTabCompleter(new NumberCompleter());
        getCommand("graceperiod").setExecutor(new GracePeriodCommand());
        getCommand("graceperiod").setTabCompleter(new NumberCompleter());

        getCommand("invsee").setExecutor(new InvSeeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
