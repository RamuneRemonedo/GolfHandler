package tokyo.ramune.golfhandler.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import tokyo.ramune.golfhandler.GolfPlugin;
import tokyo.ramune.golfhandler.config.Config;

import java.util.ArrayList;

public class MapHandler {

    private final static Config maps = GolfPlugin.getMaps();

    public static Map createMap(@NonNull String name, @NonNull String displayName, @NonNull String description) {
        if (isExistMap(name) || name.contains(".")) {
            return null;
        }
        maps.getConfig().set("maps." + name + ".start-location.display-name", displayName);

        maps.getConfig().set("maps." + name + ".start-location.description", description);

        maps.getConfig().set("maps." + name + ".start-location.world", "world");
        maps.getConfig().set("maps." + name + ".start-location.x", 0.0);
        maps.getConfig().set("maps." + name + ".start-location.y", 0.0);
        maps.getConfig().set("maps." + name + ".start-location.z", 0.0);
        maps.getConfig().set("maps." + name + ".start-location.yaw", 0);
        maps.getConfig().set("maps." + name + ".start-location.pitch", 0);

        maps.getConfig().set("maps." + name + ".goal-block-location.world", "world");
        maps.getConfig().set("maps." + name + ".goal-block-location.x", 0.0);
        maps.getConfig().set("maps." + name + ".goal-block-location.y", 0.0);
        maps.getConfig().set("maps." + name + ".goal-block-location.z", 0.0);

        GolfPlugin.getMaps().saveConfig();
        return getMap(name);
    }

    public static void deleteMap(@NonNull String name) {
        if (!isExistMap(name)) {
            return;
        }
        maps.getConfig().set("maps." + name, null);

        GolfPlugin.getMaps().saveConfig();
    }

    @Nullable
    public static Map getMap(@NonNull String name) {
        if (!isExistMap(name)) {
            return null;
        }
        try {
            String displayName, description;
            Location startLocation, goalBlockLocation;

            displayName = maps.getConfig().getString("maps." + name + ".display-name", "display name " + name);
            description = maps.getConfig().getString("maps." + name + ".description", "description " + name);
            startLocation = new Location(Bukkit.getWorld(maps.getConfig().getString("maps." + name + ".start-location.world", "world"))
                    , maps.getConfig().getDouble("maps." + name + ".start-location.x", 0.0)
                    , maps.getConfig().getDouble("maps." + name + ".start-location.y", 0.0)
                    , maps.getConfig().getDouble("maps." + name + ".start-location.z", 0.0)
                    , maps.getConfig().getInt("maps." + name + ".start-location.yaw", 0)
                    , maps.getConfig().getInt("maps." + name + ".start-location.yaw", 0));
            goalBlockLocation = new Location(Bukkit.getWorld(maps.getConfig().getString("maps." + name + ".start-location.world", "world"))
                    , maps.getConfig().getDouble("maps." + name + ".goal-block-location.x", 0.0)
                    , maps.getConfig().getDouble("maps." + name + ".goal-block-location.y", 0.0)
                    , maps.getConfig().getDouble("maps." + name + ".goal-block-location.z", 0.0));
            return new Map(name, displayName, description, startLocation, goalBlockLocation);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public static Map[] getMaps() {
        ArrayList<Map> mapArrayList = new ArrayList<>();
        for (String name : maps.getConfig().getConfigurationSection("maps").getKeys(false)) {
            Map map = getMap(name);
            if (map != null) {
                mapArrayList.add(map);
            }
        }
        return mapArrayList.toArray(new Map[0]);
    }

    public static boolean isExistMap(@NonNull String name) {
        try {
            return maps.getConfig().getConfigurationSection("maps").getKeys(false).contains(name);
        } catch (Exception e) {
            return false;
        }
    }
}
