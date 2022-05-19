package tokyo.ramune.golfhandler.listener;

import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EntityMoveListener implements Listener {

    @EventHandler
    public void onEntityMove(PlayerMoveEvent e) {
        if (e.getFrom().getX() == e.getTo().getX()
                && e.getFrom().getY() == e.getTo().getY()
                && e.getFrom().getZ() == e.getTo().getZ()) {
            return;
        }
        e.setCancelled(true);
    }
}
