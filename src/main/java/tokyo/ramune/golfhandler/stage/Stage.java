package tokyo.ramune.golfhandler.stage;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import tokyo.ramune.golfhandler.GolfPlugin;
import tokyo.ramune.golfhandler.map.Map;

import java.util.HashMap;

public class Stage {

    private String name, displayName, description;
    private HashMap<Integer, Map> stages;

    public Stage(@NonNull String name, @NonNull String displayName, @NonNull String description, HashMap<Integer, Map> stages) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.stages = stages;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NonNull String displayName) {
        GolfPlugin.getStages().getConfig().set("stages." + name + ".display-name", displayName);
        GolfPlugin.getStages().saveConfig();
        this.displayName = displayName;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        GolfPlugin.getStages().getConfig().set("stages." + name + ".description", description);
        GolfPlugin.getStages().saveConfig();
        this.description = description;
    }

    @Nullable
    public HashMap<Integer, Map> getStages() {
        return stages;
    }
}
