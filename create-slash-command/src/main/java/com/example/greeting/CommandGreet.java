package com.example.greeting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


// コマンドのクラスはCommandExecutorを実装(implements)する
public class CommandGreet implements CommandExecutor {

  /**
   * 挨拶コマンド
   *
   * @param sender コマンドの送信者
   * @param command コマンドのクラス
   * @param label コマンド名
   * @param args コマンドの引数
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // 引数(args)の内容に合わせて、senderに対して何かをするのが基本の使い方

    // 引数が1つのとき
    if (args.length == 1) {
      // 最初の引数が"hello"であれば(`/greet hello`のとき)
      if (args[0].equalsIgnoreCase("hello")) {
        sender.sendMessage("こんにちは、" + sender.getName() + "さん。");
        return true;
      }
    }

    // 引数が無かったとき(`/greet`のとき)
    if (args.length == 0) {
      sender.sendMessage("どうしましたか？");
      // 処理が正常に終了したらtrueを返す
      return true;
    }

    // それ以外なら、失敗判定としてfalseを返す
    return false;
  }

}
