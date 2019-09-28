package net.savagedev.vanishsync.bungee.messenger;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.savagedev.vanishsync.bungee.VanishSyncBungee;
import net.savagedev.vanishsync.bungee.events.VanishStatusChangeEvent;
import net.savagedev.vanishsync.common.messenger.Messenger;
import net.savagedev.vanishsync.common.protocol.ProtocolConstants;
import net.savagedev.vanishsync.common.utils.DataStreamUtils;

import java.io.DataInput;
import java.io.IOException;
import java.util.UUID;

public class BungeeMessenger implements Messenger, Listener {
    private final VanishSyncBungee plugin;

    public BungeeMessenger(final VanishSyncBungee plugin) {
        this.plugin = plugin;
        this.init();
    }

    private void init() {
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
        this.plugin.getProxy().registerChannel(ProtocolConstants.CHANNEL);
    }

    @EventHandler
    public void on(PluginMessageEvent e) {
        if (!e.getTag().equalsIgnoreCase(ProtocolConstants.CHANNEL)) {
            return;
        }

        DataInput input = DataStreamUtils.newDataInput(e.getData());

        try {
            String subChannel = input.readUTF();

            if (subChannel.equalsIgnoreCase(ProtocolConstants.VANISH)) {
                ProxiedPlayer user = this.plugin.getProxy().getPlayer(UUID.fromString(input.readUTF()));
                boolean value = input.readBoolean();

                if (user != null) {
                    if (!value) {
                        this.plugin.removeVanished(user);
                    } else {
                        this.plugin.addVanished(user);
                    }
                }

                this.plugin.getProxy().getPluginManager().callEvent(new VanishStatusChangeEvent(user, value));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        this.plugin.getProxy().unregisterChannel(ProtocolConstants.CHANNEL);
    }
}
