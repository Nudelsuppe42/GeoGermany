package de.nudelsuppe.geoguessr;

import de.nudelsuppe.geoguessr.commands.GermanyGuessrCommand;
import de.nudelsuppe.geoguessr.game.ChatListener;
import de.nudelsuppe.geoguessr.game.Game;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Main extends JavaPlugin {

    private static Main instance;
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        instance = this;

        getCommand("germanyguessr").setExecutor(new GermanyGuessrCommand());

        getServer().getPluginManager().registerEvents(new ChatListener(),instance);

        if (!config.contains("locations")) {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("0 0 0", "Example");
            Game.setLocations(temp);
            config.createSection("locations", Game.getLocations());
            config.options().copyDefaults(true);
            saveConfig();
        }
        Game.setLocations(config.getConfigurationSection("locations").getValues(false));


    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static FileConfiguration getConfigFile() {

        return instance.config;
    }
}
