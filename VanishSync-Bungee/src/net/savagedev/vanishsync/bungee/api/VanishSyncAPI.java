package net.savagedev.vanishsync.bungee.api;

import com.google.common.collect.ImmutableSet;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface VanishSyncAPI {
    ImmutableSet<ProxiedPlayer> getVanishedPlayers();
}
