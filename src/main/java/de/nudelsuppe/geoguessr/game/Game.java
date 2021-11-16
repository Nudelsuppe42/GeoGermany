package de.nudelsuppe.geoguessr.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    private static HashMap<Player,String> playing = new HashMap<>();
    private static Map<String, Object> locations = new HashMap<>();



    public static void play(Player player) {
        int rnd = new Random().nextInt(getAllLocationStrings().length);
        System.out.println(rnd);
        String location = getAllLocationStrings()[rnd].toString();
        addPlaying(player,location);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.teleport(convertLocation(location));
        player.sendMessage(" ");
        player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Du hast eine Runde GermanyGuessr gestartet!");
        player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Schau dich um und versuche ");
        player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > herrauszufinden wo du dich befindest!");
        player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Wenn du fertig bist schreibe die Stadt in den Chat!");
        player.sendMessage(" ");
    }
    public static void evaluate(String answer, Player player) {
        String right = locations.get(playing.get(player)).toString();
        if(answer.equalsIgnoreCase(right.toLowerCase())) {
            player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Richtig! Du bist in "+ChatColor.AQUA+right+ChatColor.GRAY+"!");
            player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Du kannst jetzt eine neue Runde beginnen!");
            playing.remove(player);
        }else {
            player.sendMessage(ChatColor.AQUA+"GermanyGuessr" + ChatColor.GRAY +" > Leider falsch, du kannst es aber nocheinmal versuchen!");
        }

    }

    public static HashMap<Player, String> getPlaying() {
        return playing;
    }
    public static Map<String, Object> getLocations() {
        return locations;
    }
    public static void setLocations(Map<String, Object> nlocations) {
        locations = nlocations;
    }
    public static void addPlaying(Player player,String location) {
        if(playing.containsKey(player)) return;
        playing.put(player, location);
    }
    public static void removePlaying(Player player) {
        if(!playing.containsKey(player)) return;
        playing.remove(player);
    }
    public static Object[] getAllLocationStrings() {
        return locations.keySet().toArray();
    }
    public static boolean isPlaying(Player player) {
        return playing.containsKey(player);
    }
    public static Location convertLocation(String location) {
        String[] arg = location.split(" ");
        double[] parsed = new double[3];
        for (int a = 0; a < 3; a++) {
            parsed[a] = Double.parseDouble(arg[a]);
        }

       return new Location(Bukkit.getWorld("world"), parsed[0], parsed[1], parsed[2]);
    }
}
