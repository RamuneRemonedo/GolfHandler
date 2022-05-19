package tokyo.ramune.golfhandler.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatusHandler {

    private static HashMap<UUID, PlayerStatus> playerStatusHashMap = new HashMap<>();

    public static PlayerStatus getPlayerStatus(@NonNull UUID player) {
        return playerStatusHashMap.getOrDefault(player, PlayerStatus.OFFLINE);
    }

    public static void setPlayerStatus(@NonNull UUID player, @NonNull PlayerStatus status) {
        if (playerStatusHashMap.containsKey(player)) {
            playerStatusHashMap.replace(player, status);
        } else {
            playerStatusHashMap.put(player, status);
        }
    }

    public static void removePlayerStatus(@NonNull UUID player) {
        if (!playerStatusHashMap.containsKey(player)) {
            return;
        }
        playerStatusHashMap.remove(player);
    }

    public static void initializePlayerStatuses() {
        playerStatusHashMap = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            setPlayerStatus(player.getUniqueId(), PlayerStatus.ONLINE);
        }
    }
}
