package tokyo.ramune.golfhandler.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getHitBlock() == null) {
            return;
        }
        if (!(e.getEntity().getShooter() instanceof Player)) {
            return;
        }
        Player shooter = (Player) e.getEntity().getShooter();
        Location hitLocation = e.getHitBlock().getLocation().clone();
        int modX = e.getHitBlockFace().getModX();
        int modY = e.getHitBlockFace().getModY();
        int modZ = e.getHitBlockFace().getModZ();
        if (modX == 1) modX ++;
        if (modY == 1) modY ++;
        if (modZ == 1) modZ ++;
        hitLocation.setDirection(shooter.getLocation().getDirection());
        hitLocation.add(modX, modY, modZ);
        while (!hitLocation.getBlock().getType().equals(Material.AIR)) {
            hitLocation.add(0, 1, 0);
        }
        shooter.playSound(hitLocation, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 2);
        shooter.teleport(hitLocation);
        e.getEntity().remove();
    }
}
