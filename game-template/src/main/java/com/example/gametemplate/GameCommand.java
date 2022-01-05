package com.example.gametemplate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
        return this.leave(player);
      } else if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
        return this.join(player);
      }
    } else {
      System.out.println("このコマンドはコンソールから使用できません。");
      return false;
    }
    return false;
  }

  /**
   * アリーナからの離脱処理を行う
   *
   * @param player 対象プレイヤー
   * @return コマンド成功判定
   */
  private boolean leave(Player player) {
    if (GameManager.isPlaying(player)) {
      GameManager.getArena().removePlayer(player);
      player.sendMessage(ChatColor.GREEN + "アリーナから離脱しました。");
      return true;
    }

    player.sendMessage(ChatColor.RED + "アリーナに入っていません。");
    return false;
  }

  /**
   * アリーナへの参加処理を行う
   *
   * @param player 対象プレイヤー
   * @return コマンド成功判定
   */
  private boolean join(Player player) {
    if (GameManager.getArena().isRecruiting()) {
      GameManager.getArena().addPlayer(player);
      player.sendMessage(ChatColor.GREEN + "アリーナに参加しました。");
      return true;
    }

    player.sendMessage(ChatColor.RED + "現在アリーナに入れません。");
    return false;
  }

}
