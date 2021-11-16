package de.nudelsuppe.geoguessr.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!Game.isPlaying(event.getPlayer())) return;
        Game.evaluate(event.getMessage(),event.getPlayer());
        event.setCancelled(true);
    }
}
