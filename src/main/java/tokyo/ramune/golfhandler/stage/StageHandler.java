package tokyo.ramune.golfhandler.stage;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import tokyo.ramune.golfhandler.GolfPlugin;
import tokyo.ramune.golfhandler.config.Config;
import tokyo.ramune.golfhandler.map.Map;
import tokyo.ramune.golfhandler.map.MapHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class StageHandler {

    private final static Config stages = GolfPlugin.getStages();

    public static Stage createStage(@NonNull String name, @NonNull String displayName, @NonNull String description) {
        if (isExistStage(name)) {
            return getStage(name);
        }
        stages.getConfig().set("stages." + name + ".display-name", displayName);
        stages.getConfig().set("stages." + name + ".description", description);
        stages.saveConfig();
        return getStage(name);
    }

    public static void deleteStage(@NonNull String name) {
        if (!isExistStage(name)) {
            return;
        }
        stages.getConfig().set("stages." + name, null);
        stages.saveConfig();
    }

    @Nullable
    public static Stage getStage(@NonNull String name) {
        if (isExistStage(name)) {
            String displayName, description;
            HashMap<Integer, Map> _stages = new HashMap<>();
            displayName = stages.getConfig().getString("stages." + name + ".display-name", "display name " + name);
            description = stages.getConfig().getString("stages." + name + ".description", "description " + name);

            try {
                for (String i : stages.getConfig().getConfigurationSection("stages." + name).getKeys(false)) {
                    int _i = Integer.parseInt(i);
                    _stages.put(_i, MapHandler.getMap(GolfPlugin.getStages().getConfig().getString("stages." + name + "." + _i)));
                }
            } catch (Exception e) {
                _stages = null;
            }
            return new Stage(name, displayName, description, _stages);
        } else {
            return null;
        }
    }

    @Nullable
    public static Stage[] getStages() {
        ArrayList<Stage> _stages = new ArrayList<>();
        for (String name : stages.getConfig().getConfigurationSection("stages").getKeys(false)) {
            _stages.add(getStage(name));
        }
        return _stages.toArray(new Stage[0]);
    }

    public static boolean isExistStage(@NonNull String name) {
        try {
            return stages.getConfig().getConfigurationSection("stages").getKeys(false).contains(name);
        } catch (Exception e) {
            return false;
        }
    }
}
