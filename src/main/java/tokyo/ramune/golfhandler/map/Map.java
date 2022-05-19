package tokyo.ramune.golfhandler.map;

import org.bukkit.Location;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.golfhandler.GolfPlugin;

public class Map {

    private String name, displayName, description;
    private Location startLocation, goalBlockLocation;

    public Map(@NonNull String name, @NonNull String displayName, @NonNull String description, @NonNull Location startLocation, @NonNull Location goalBlockLocation) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.startLocation = startLocation;
        this.goalBlockLocation = goalBlockLocation;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDisplayName() {
        return description;
    }

    public void setDisplayName(@NonNull String displayName) {
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".display-name", displayName);
        GolfPlugin.getMaps().saveConfig();
        this.displayName = displayName;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".description", description);
        GolfPlugin.getMaps().saveConfig();
        this.description = description;
    }

    @NonNull
    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(@NonNull Location startLocation) {
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.world", startLocation.getWorld().getName());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.x", startLocation.getX());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.y", startLocation.getY());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.z", startLocation.getZ());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.yaw", startLocation.getYaw());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.pitch", startLocation.getPitch());
        GolfPlugin.getMaps().saveConfig();
        this.startLocation = startLocation;
    }

    @NonNull
    public Location getGoalBlockLocation() {
        return goalBlockLocation;
    }

    public void setGoalBlockLocation(@NonNull Location goalBlockLocation) {
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".goal-block-location.world", goalBlockLocation.getWorld().getName());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.x", goalBlockLocation.getBlockX());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.y", goalBlockLocation.getBlockY());
        GolfPlugin.getMaps().getConfig().set("maps." + name + ".start-location.z", goalBlockLocation.getZ());
        GolfPlugin.getMaps().saveConfig();
        this.goalBlockLocation = goalBlockLocation;
    }
}
