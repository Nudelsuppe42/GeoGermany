package de.nudelsuppe.geoguessr.commands;

import de.nudelsuppe.geoguessr.Main;
import de.nudelsuppe.geoguessr.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GermanyGuessrCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (Game.isPlaying((Player) sender) && args.length > 0) {
                Game.evaluate(args[0], (Player) sender);
            } else if (args.length > 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    Player player = (Player) sender;
                    String loc = player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ();
                    Map<String, Object> map = Game.getLocations();
                    map.put(loc, args[1]);
                    Main.getConfigFile().set("locations", map);
                    Main.getInstance().saveConfig();
                    player.sendMessage(ChatColor.AQUA + "GermanyGuessr" + ChatColor.GRAY + " > " + ChatColor.AQUA + args[1] + ChatColor.GRAY + " gespeichert für die Koordinaten " + loc);
                }
            } else {
                Game.play((Player) sender);
            }
        }
        return true;
    }
}
