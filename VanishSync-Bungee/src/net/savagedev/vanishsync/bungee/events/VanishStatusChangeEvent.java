package net.savagedev.vanishsync.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class VanishStatusChangeEvent extends Event {
    private final ProxiedPlayer player;
    private final boolean value;

    public VanishStatusChangeEvent(final ProxiedPlayer player, final boolean value) {
        this.player = player;
        this.value = value;
    }

    public ProxiedPlayer getPlayer() {
        return this.player;
    }

    public boolean getValue() {
        return this.value;
    }
}
