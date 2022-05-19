package tokyo.ramune.golfhandler.command;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.golfhandler.util.Chat;

public class UUIDCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "uuid";
    }

    @Override
    public @NonNull String getDescription() {
        return "プレイヤーのUUIDをプレイヤー名から取得します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.hasPermission("GolfHandler.command.uuid")) {
            return;
        }
        if (args.length == 1) {
            Chat.sendMessage(player, ChatColor.AQUA + "/golf uuid [プレイヤー名]", true);
            return;
        }
        try {
            TextComponent tc = new TextComponent();
            tc.setText(Bukkit.getPlayerUniqueId(args[1]).toString() + ChatColor.YELLOW + "\nクリックでクリップボードにコピー");
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, Bukkit.getPlayerUniqueId(args[1]).toString()));
            tc.setUnderlined(true);
            Chat.sendMessage(player, ChatColor.GREEN + "取得完了!", true);
            player.spigot().sendMessage(tc);
        } catch (Exception e) {
            Chat.sendMessage(player, ChatColor.RED + "プレイヤーが見つかりませんでした", true);
            Chat.sendMessage(player, ChatColor.AQUA + "/golf uuid [プレイヤー名]", true);
        }
    }
}
