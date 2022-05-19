package tokyo.ramune.golfhandler.player;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.golfhandler.database.SQL;
import tokyo.ramune.golfhandler.database.SQLDateTime;

import java.util.UUID;

public class PlayerDatabaseHandler {

    public static void createTable() {
        if (!SQL.tableExists("players")) {
            SQL.createTable("players", "uuid TEXT NOT NULL, latestJoinDate DATETIME NOT NULL, level INT NOT NULL, exp INT NOT NULL");
        }
    }

    public static void registerPlayer(@NonNull Player player) {
        registerPlayer(player.getUniqueId());
    }

    public static void registerPlayer(@NonNull UUID uuid) {
        if (!isExistPlayer(uuid)) {
            SQL.insertData("uuid, latestJoinDate", "'" + uuid.toString() + "', '" + new SQLDateTime().getSQLDate() + "', 1, 0", "players");
        }
    }

    @Nullable
    public static SQLDateTime getLatestJoinDate(@NonNull Player player) {
        return getLatestJoinDate(player.getUniqueId());
    }

    @Nullable
    public static SQLDateTime getLatestJoinDate(@NonNull UUID uuid) {
        if (isExistPlayer(uuid)) {
            return new SQLDateTime(String.valueOf(SQL.get("latestJoinDate", "uuid", "=", uuid.toString(), "players")));
        } else {
            return null;
        }
    }

    public static void updateLatestJoinDate(@NonNull Player player) {
        updateLatestJoinDate(player.getUniqueId());
    }

    public static void updateLatestJoinDate(@NonNull UUID uuid) {
        if (isExistPlayer(uuid)) {
            SQL.upsert("latestJoinDate", new SQLDateTime().getSQLDate(), "uuid", uuid.toString(), "players");
        }
    }

    public static int getLevel(@NonNull Player player) {
        return getLevel(player.getUniqueId());
    }

    public static int getLevel(@NonNull UUID uuid) {
        if (isExistPlayer(uuid)) {
            return Integer.parseInt(String.valueOf(SQL.get("level", "uuid", "=", uuid.toString(), "players")));
        } else {
            return 0;
        }
    }

    public static void setLevel(@NonNull Player player, int level) {
        setLevel(player.getUniqueId(), level);
    }

    public static void setLevel(@NonNull UUID uuid, int level) {
        if (isExistPlayer(uuid)) {
            SQL.upsert("level", String.valueOf(level), "uuid", uuid.toString(), "players");
        }
    }

    public static int getEXP(@NonNull Player player) {
        return getLevel(player.getUniqueId());
    }

    public static int getEXP(@NonNull UUID uuid) {
        if (isExistPlayer(uuid)) {
            return Integer.parseInt(String.valueOf(SQL.get("exp", "uuid", "=", uuid.toString(), "players")));
        } else {
            return 0;
        }
    }

    public static void setEXP(@NonNull Player player, int exp) {
        setLevel(player.getUniqueId(), exp);
    }

    public static void setEXP(@NonNull UUID uuid, int exp) {
        if (isExistPlayer(uuid)) {
            SQL.upsert("exp", String.valueOf(exp), "uuid", uuid.toString(), "players");
        }
    }

    public static boolean isExistPlayer(@NonNull Player player) {
        return isExistPlayer(player.getUniqueId());
    }

    public static boolean isExistPlayer(@NonNull UUID uuid) {
        return SQL.exists("uuid", uuid.toString(), "players");
    }
}
