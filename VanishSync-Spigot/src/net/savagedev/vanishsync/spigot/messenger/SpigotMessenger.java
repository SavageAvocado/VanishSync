package net.savagedev.vanishsync.spigot.messenger;

import net.savagedev.vanishsync.common.messenger.Messenger;
import net.savagedev.vanishsync.common.protocol.ProtocolConstants;
import net.savagedev.vanishsync.spigot.VanishSyncSpigot;

public class SpigotMessenger implements Messenger {
    private final VanishSyncSpigot plugin;

    public SpigotMessenger(final VanishSyncSpigot plugin) {
        this.plugin = plugin;
        this.init();
    }

    private void init() {
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(this.plugin, ProtocolConstants.CHANNEL);
    }

    @Override
    public void close() {
        this.plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(this.plugin, ProtocolConstants.CHANNEL);
    }
}
