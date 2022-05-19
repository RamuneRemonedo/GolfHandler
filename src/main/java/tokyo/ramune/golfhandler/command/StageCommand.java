package tokyo.ramune.golfhandler.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.golfhandler.stage.Stage;
import tokyo.ramune.golfhandler.stage.StageHandler;
import tokyo.ramune.golfhandler.util.Chat;

public class StageCommand implements SubCommand {

    @Override
    public @NonNull String getSubCommand() {
        return "stage";
    }

    @Override
    public @NonNull String getDescription() {
        return "ステージについて";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.hasPermission("GolfHandler.command.stage")) {
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
                if (StageHandler.isExistStage(args[2])) {
                    Chat.sendMessage(player, ChatColor.RED + "[エラー] そのステージ名はすでに存在します。", true);
                    return;
                }
                StageHandler.createStage(args[2], args[3], args[4]);
                Chat.sendMessage(player, ChatColor.GREEN + "ステージを作成しました。 作成したステージ名: " + args[2], true);
                break;

            case "delete":
                if (args.length < 3) {
                    sendHelp(player);
                    return;
                }
                if (!StageHandler.isExistStage(args[2])) {
                    Chat.sendMessage(player, ChatColor.RED + "[エラー] そのステージ名は存在しません。", true);
                    Chat.sendMessage(player, ChatColor.YELLOW + "[ヒント] /golf stage list でステージ一覧を確認できます。", true);
                    return;
                }
                StageHandler.deleteStage(args[2]);
                Chat.sendMessage(player, ChatColor.GREEN + "ステージを削除しました。 削除したステージ名: " + args[2], true);
                break;

            case "list":
                for (Stage stage : StageHandler.getStages()) {
                    Chat.sendMessage(player, ChatColor.YELLOW + "・" + stage.getName(), true);
                }
                break;

            default: sendHelp(player);
        }
    }

    public void sendHelp(@NonNull Player player) {
        Chat.sendMessage(player, ChatColor.LIGHT_PURPLE + "[ヘルプ]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "[サブコマンド]" + ChatColor.GREEN + " [説明]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "create [作成するステージ名] [ステージの表示名] [ステージの説明] " + ChatColor.GREEN + " ステージを作成します", true);
        Chat.sendMessage(player, ChatColor.AQUA + "delete [作成するステージ名] " + ChatColor.GREEN + " ステージを削除します", true);
        Chat.sendMessage(player, ChatColor.AQUA + "list " + ChatColor.GREEN + " ステージ一覧を表示します", true);
    }
}
