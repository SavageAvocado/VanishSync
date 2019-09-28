package net.savagedev.vanishsync.spigot.listeners;

import net.ess3.api.events.VanishStatusChangeEvent;
import net.savagedev.vanishsync.common.protocol.ProtocolConstants;
import net.savagedev.vanishsync.common.utils.DataStreamUtils;
import net.savagedev.vanishsync.spigot.VanishSyncSpigot;
import net.savagedev.vanishsync.spigot.messenger.receiver.SpigotMessageReceiver;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;

public class VanishStatusChangeE implements Listener {
    private final VanishSyncSpigot plugin;

    public VanishStatusChangeE(final VanishSyncSpigot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(VanishStatusChangeEvent e) {
        Player user = e.getAffected().getBase();
        this.plugin.getMessenger().send(new SpigotMessageReceiver<>(this.plugin, user), DataStreamUtils.toByteArray(dataOutput -> {
            try {
                dataOutput.writeUTF(ProtocolConstants.VANISH);
                dataOutput.writeUTF(user.getUniqueId().toString());
                dataOutput.writeBoolean(e.getValue());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
    }
}
