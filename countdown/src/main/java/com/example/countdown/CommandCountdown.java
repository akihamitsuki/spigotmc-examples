package com.example.countdown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCountdown implements CommandExecutor {

  private CountDownTimer countDownTimer = null;

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (args.length == 1) {
      if (args[0].equals("start")) {
        // インスタンスを作成していれば停止処理を入れて重複を避ける
        if (countDownTimer != null) {
          countDownTimer.cancel();
        }
        // インスタンスを作成
        countDownTimer = new CountDownTimer();
        // カウントダウンを開始
        countDownTimer.start();

        return true;
      } else if (args[0].equals("stop")) {
        // インスタンスを作成していなければ停止処理をしないように
        if (countDownTimer != null) {
          countDownTimer.stop();
        }

        return true;
      }
    }

    return false;
  }
}
