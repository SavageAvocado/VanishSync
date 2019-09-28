package net.savagedev.vanishsync.spigot.messenger.receiver;

import net.savagedev.vanishsync.common.messenger.receiver.MessageReceiver;
import net.savagedev.vanishsync.common.protocol.ProtocolConstants;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

public class SpigotMessageReceiver<T extends PluginMessageRecipient> implements MessageReceiver {
    private final JavaPlugin plugin;
    private final T recipient;

    public SpigotMessageReceiver(final JavaPlugin plugin, final T recipient) {
        this.recipient = recipient;
        this.plugin = plugin;
    }

    @Override
    public void sendData(byte[] data) {
        this.recipient.sendPluginMessage(this.plugin, ProtocolConstants.CHANNEL, data);
    }
}
