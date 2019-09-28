package net.savagedev.vanishsync.bungee.listeners;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import net.savagedev.vanishsync.bungee.api.VanishSyncAPI;

public class ProxyPingE implements Listener {
    private final VanishSyncAPI api;

    public ProxyPingE(final VanishSyncAPI api) {
        this.api = api;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(ProxyPingEvent e) {
        ServerPing response = e.getResponse();
        ServerPing.Players players = response.getPlayers();
        players.setOnline(players.getOnline() - this.api.getVanishedPlayers().size());
        response.setPlayers(players);
        e.setResponse(response);
    }
}
