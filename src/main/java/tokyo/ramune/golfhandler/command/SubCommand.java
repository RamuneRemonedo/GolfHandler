package tokyo.ramune.golfhandler.command;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;


public interface SubCommand {

    @NonNull String getSubCommand();

    @NonNull String getDescription();

    void onCommand(@NonNull Player player, String[] args);
}
