package tokyo.ramune.golfhandler.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.golfhandler.player.PlayerDatabaseHandler;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerDatabaseHandler.registerPlayer(player);
        PlayerDatabaseHandler.updateLatestJoinDate(player);
    }
}
