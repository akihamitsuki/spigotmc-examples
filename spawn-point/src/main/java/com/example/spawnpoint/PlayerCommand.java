package com.example.spawnpoint;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor {

  private PlayerManager playerManager;

  /**
   * コンストラクタ
   *
   * @param playerManager
   */
  public PlayerCommand(PlayerManager playerManager) {
    // PlayerListenrのコンストラクタでやっている操作と同じ
    this.playerManager = playerManager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // 引数の数が1であれば
    if (args.length == 1) {
      // その引数が特定の値であれば
      if (args[0].equals("spawnpoint")) {
        if (sender instanceof Player) {
          // コマンド送信者をプレイヤー型に変換する
          Player player = (Player) sender;
          // プレイヤー管理インスタンスのロビー座標に、現在の座標を登録する
          playerManager.lobby = player.getLocation();
          // コマンド実行結果をコマンド実行者に対して表示する
          player.sendMessage("ログインしたときのスポーンポイントを、この場所に設定しました。");
          // このコマンドは成功である
          return true;
        }
        sender.sendMessage("このコマンドはコンソール画面からは使えません");
        // このコマンドは失敗である
        return false;
      }
    }
    return false;
  }
}
