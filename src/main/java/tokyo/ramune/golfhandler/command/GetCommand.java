package tokyo.ramune.golfhandler.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.golfhandler.database.SQL;
import tokyo.ramune.golfhandler.util.Chat;

public class GetCommand implements SubCommand {


    @Override
    public @NonNull String getSubCommand() {
        return "get";
    }

    @Override
    public @NonNull String getDescription() {
        return "プレイヤーの情報を取得します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.hasPermission("GolfHandler.command.get")) {
            return;
        }
        if (args.length < 5) {
            sendHelp(player);
            return;
        }
        String data = String.valueOf(SQL.get(args[1], args[2], args[3], args[4], args[5]));
        if (data == null || data.equals("")) {
            Chat.sendMessage(player, ChatColor.RED + "[エラー] プレイヤー もしくは データが見つかりませんでした", true);
        } else {
            Chat.sendMessage(player, data, false);
        }
    }

    public void sendHelp(Player player) {
        Chat.sendMessage(player, ChatColor.LIGHT_PURPLE + "[ヘルプ] /golf get " + ChatColor.RED + "[Selected]" + ChatColor.LIGHT_PURPLE + " [Column]" + ChatColor.GREEN + " [Logic Gate]" + ChatColor.YELLOW + " [Data]" + ChatColor.GRAY + " [Table]", true);
    }
}
