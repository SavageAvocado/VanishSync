package net.savagedev.vanishsync.spigot;

import com.earth2me.essentials.Essentials;
import net.savagedev.vanishsync.common.messenger.Messenger;
import net.savagedev.vanishsync.spigot.listeners.JoinE;
import net.savagedev.vanishsync.spigot.listeners.VanishStatusChangeE;
import net.savagedev.vanishsync.spigot.messenger.SpigotMessenger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class VanishSyncSpigot extends JavaPlugin {
    private final Messenger messenger = new SpigotMessenger(this);
    private Essentials essentials;

    @Override
    public void onEnable() {
        this.hookEssentials();
        this.initListeners();
    }

    @Override
    public void onDisable() {
        this.messenger.close();
    }

    private void hookEssentials() {
        if (this.getServer().getPluginManager().getPlugin("Essentials") != null) {
            this.getServer().getLogger().log(Level.INFO, "[VanishSync] Hooking into Essentials.");
            this.essentials = Essentials.getPlugin(Essentials.class);
        } else {
            this.getServer().getLogger().log(Level.INFO, "[VanishSync] Essentials not found. Disabling plugin.");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void initListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new VanishStatusChangeE(this), this);
        pluginManager.registerEvents(new JoinE(this), this);
    }

    public Essentials getEssentials() {
        return this.essentials;
    }

    public Messenger getMessenger() {
        return this.messenger;
    }
}
