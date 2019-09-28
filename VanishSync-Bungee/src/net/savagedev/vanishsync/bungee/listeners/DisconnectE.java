package net.savagedev.vanishsync.bungee.listeners;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.savagedev.vanishsync.bungee.VanishSyncBungee;

public class DisconnectE implements Listener {
    private final VanishSyncBungee plugin;

    public DisconnectE(final VanishSyncBungee plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerDisconnectEvent e) {
        if (this.plugin.getVanishedPlayers().contains(e.getPlayer())) {
            this.plugin.removeVanished(e.getPlayer());
        }
    }
}
