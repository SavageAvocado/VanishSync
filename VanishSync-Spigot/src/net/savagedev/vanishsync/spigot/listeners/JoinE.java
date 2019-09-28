package net.savagedev.vanishsync.spigot.listeners;

import com.earth2me.essentials.User;
import net.savagedev.vanishsync.common.protocol.ProtocolConstants;
import net.savagedev.vanishsync.common.utils.DataStreamUtils;
import net.savagedev.vanishsync.spigot.VanishSyncSpigot;
import net.savagedev.vanishsync.spigot.messenger.receiver.SpigotMessageReceiver;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinE implements Listener {
    private final VanishSyncSpigot plugin;

    public JoinE(final VanishSyncSpigot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerJoinEvent e) {
        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(this.plugin, () -> {
            Player player = e.getPlayer();
            User user = this.plugin.getEssentials().getUser(player);

            if (user.isVanished()) {
                this.plugin.getMessenger().send(new SpigotMessageReceiver<>(this.plugin, player), DataStreamUtils.toByteArray(dataOutput -> {
                    try {
                        dataOutput.writeUTF(ProtocolConstants.VANISH);
                        dataOutput.writeUTF(player.getUniqueId().toString());
                        dataOutput.writeBoolean(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }));
            }
        }, 1L);
    }
}
