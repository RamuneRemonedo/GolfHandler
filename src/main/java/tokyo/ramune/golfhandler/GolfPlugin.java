package tokyo.ramune.golfhandler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.golfhandler.command.CommandHandler;
import tokyo.ramune.golfhandler.command.TabCompleterHandler;
import tokyo.ramune.golfhandler.config.Config;
import tokyo.ramune.golfhandler.database.DatabaseHandler;
import tokyo.ramune.golfhandler.database.MySQL;
import tokyo.ramune.golfhandler.listener.ListenerHandler;
import tokyo.ramune.golfhandler.player.PlayerHandler;

public final class GolfPlugin extends JavaPlugin {

    private static JavaPlugin plugin;
    private static ProtocolManager protocolManager;
    private static Config config, maps, stages;

    @Override
    public void onEnable() {
        plugin = this;

        config = new Config(this, "config.yml");
        config.saveDefaultConfig();
        maps = new Config(this, "map.yml");
        maps.saveDefaultConfig();
        stages = new Config(this, "stage.yml");
        stages.saveDefaultConfig();
        MySQL.connect(true);
        DatabaseHandler.createTables();
        PlayerHandler.initializePlayers();

        new ListenerHandler(this);
        new CommandHandler(this);
        new TabCompleterHandler(this);

        protocolManager = ProtocolLibrary.getProtocolManager();

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
        config.saveConfig();
        getLogger().info("The plugin has been disabled.");
    }

    public static JavaPlugin getInstance() {
        return plugin;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public static Config getConfiguration() {
        return config;
    }

    public static Config getMaps() {
        return maps;
    }

    public static Config getStages() {
        return stages;
    }
}
