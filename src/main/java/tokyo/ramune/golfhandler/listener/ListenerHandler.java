package tokyo.ramune.golfhandler.listener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerHandler {

    public ListenerHandler(JavaPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new ProjectileHitListener(), plugin);
        pm.registerEvents(new PlayerQuitListener(), plugin);
        pm.registerEvents(new EntityMoveListener(), plugin);
    }
}
