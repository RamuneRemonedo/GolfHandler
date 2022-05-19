package tokyo.ramune.golfhandler.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.golfhandler.map.Map;
import tokyo.ramune.golfhandler.map.MapHandler;
import tokyo.ramune.golfhandler.util.Chat;

public class MapCommand implements SubCommand {

    @Override
    public @NonNull String getSubCommand() {
        return "map";
    }

    @Override
    public @NonNull String getDescription() {
        return "マップについて";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.hasPermission("GolfHandler.command.map")) {
            return;
        }
        if (args.length < 2) {
            sendHelp(player);
            return;
        }
        switch (args[1]) {
            case "create":
                if (args.length < 5) {
                    sendHelp(player);
                    return;
                }
                if (MapHandler.isExistMap(args[2])) {
                    Chat.sendMessage(player, ChatColor.RED + "[エラー] その名前のマップはすでに存在します。", true);
                    return;
                }
                MapHandler.createMap(args[2], args[3], args[4]);
                Chat.sendMessage(player, ChatColor.GREEN + "作成完了! 作成したマップ名: " + args[2], true);
                Chat.sendMessage(player, ChatColor.GREEN + "マップ作成後は必ずスタート位置とゴールブロック位置を指定してくださいね", true);
                break;

            case "delete":
                if (args.length < 3) {
                    sendHelp(player);
                    return;
                }
                if (!MapHandler.isExistMap(args[2])) {
                    Chat.sendMessage(player, ChatColor.RED + "[エラー] その名前のマップは存在しません。", true);
                    return;
                }
                MapHandler.deleteMap(args[2]);
                Chat.sendMessage(player, ChatColor.GREEN + "削除が完了しました。 削除したマップ: " + args[2], true);
                break;

            case "get":
                if (args.length < 3) {
                    sendHelp(player);
                    return;
                }
                if (!MapHandler.isExistMap(args[2])) {
                    Chat.sendMessage(player, ChatColor.RED + "[エラー] その名前のマップは存在しません。", true);
                    Chat.sendMessage(player, ChatColor.YELLOW + "[ヒント] /golf map list でマップ一覧を確認できます。", true);
                    return;
                }

                break;

            case "list":
                Chat.sendMessage(player, ChatColor.LIGHT_PURPLE + "[マップ一覧]", true);
                for (Map map : MapHandler.getMaps()) {
                    Chat.sendMessage(player, ChatColor.YELLOW + "・" + map.getName(), true);
                }
                break;

            default: sendHelp(player);
        }
    }

    public void sendHelp(@NonNull Player player) {
        Chat.sendMessage(player, ChatColor.LIGHT_PURPLE + "[ヘルプ]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "[サブコマンド]" + ChatColor.GREEN + " [説明]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "create [作成するマップ名] [マップの表示名] [マップの説明] " + ChatColor.GREEN + " マップを作成します", true);
        Chat.sendMessage(player, ChatColor.AQUA + "delete [作成するマップ名] " + ChatColor.GREEN + " マップを削除します", true);
        Chat.sendMessage(player, ChatColor.AQUA + "list " + ChatColor.GREEN + " マップ一覧を表示します", true);
    }
}
