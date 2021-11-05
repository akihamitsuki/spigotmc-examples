package com.example.countdown;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownTimer extends BukkitRunnable {

  /** 秒数 */
  private int seconds;

  public CountDownTimer() {
    // 秒数を設定
    this.seconds = 20;
  }

  /**
   * カウントダウンを開始
   */
  public void start() {
    // runTaskTimer()を実行した時点で起動
    // Main.getInstance(): JavaPluginを指定
    // 0: 0ティック後から開始
    // 20: 20ティック(1秒)ごとにrun()を実行
    this.runTaskTimer(Main.getInstance(), 0, 20);
    Bukkit.broadcastMessage("カウントダウンを開始します。");
  }

  /**
   * カウントダウンを停止
   */
  public void stop() {
    Bukkit.broadcastMessage("カウントダウンを停止しました。");
    this.cancel();
  }

  /**
   * 指定間隔ごとに、このメソッドを実行（今回は20ティック=1秒ごと）
   */
  @Override
  public void run() {
    // 0以下になったとき
    if (seconds <= 0) {
      Bukkit.broadcastMessage("終了!");
      // cancel()を実行すると停止
      this.cancel();
      return;
    }

    // 5秒ごと(5で割った余りが0のとき)
    // または5秒未満のときに残り時間を表示
    if (seconds % 5 == 0 || seconds < 5) {
      Bukkit.broadcastMessage("残り" + seconds + "秒。");
    }

    // 1ずつ減らす
    seconds--;
  }

}
