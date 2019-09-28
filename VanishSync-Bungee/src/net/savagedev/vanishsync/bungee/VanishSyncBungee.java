package net.savagedev.vanishsync.bungee;

import com.google.common.collect.ImmutableSet;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.savagedev.vanishsync.bungee.api.VanishSyncAPI;
import net.savagedev.vanishsync.bungee.listeners.DisconnectE;
import net.savagedev.vanishsync.bungee.listeners.ProxyPingE;
import net.savagedev.vanishsync.bungee.messenger.BungeeMessenger;

import java.util.HashSet;
import java.util.Set;

public class VanishSyncBungee extends Plugin implements VanishSyncAPI {
    private final Set<ProxiedPlayer> vanished = new HashSet<>();
    private BungeeMessenger messenger;

    @Override
    public void onEnable() {
        this.messenger = new BungeeMessenger(this);
        this.initListeners();
    }

    @Override
    public void onDisable() {
        this.messenger.close();
    }

    private void initListeners() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
        pluginManager.registerListener(this, new DisconnectE(this));
        pluginManager.registerListener(this, new ProxyPingE(this));
    }

    public void addVanished(ProxiedPlayer player) {
        this.vanished.add(player);
    }

    public void removeVanished(ProxiedPlayer player) {
        this.vanished.remove(player);
    }

    @Override
    public ImmutableSet<ProxiedPlayer> getVanishedPlayers() {
        return ImmutableSet.copyOf(this.vanished);
    }
}
