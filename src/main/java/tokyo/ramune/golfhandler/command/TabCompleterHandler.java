package tokyo.ramune.golfhandler.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabCompleterHandler implements TabCompleter {

    public TabCompleterHandler(JavaPlugin plugin) {
        plugin.getCommand("golf").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand _command : CommandHandler.getSubCommands()) {
                StringUtil.copyPartialMatches(args[0], Collections.singleton(_command.getSubCommand()), completions);
            }
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}
