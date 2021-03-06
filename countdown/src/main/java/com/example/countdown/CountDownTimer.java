package com.example.countdown;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import net.kyori.adventure.text.Component;

public class CountDownTimer extends BukkitRunnable {

  /** 秒数 */
  private int seconds = 20;

  /**
   * カウントダウンを開始
   */
  public void start() {
    // runTaskTimer()を実行した時点で起動
    // Main.getInstance(): JavaPluginを指定
    // 0: 0ティック後から開始
    // 20: 20ティック(1秒)ごとにrun()を実行
    this.runTaskTimer(Main.getInstance(), 0, 20);
    Bukkit.broadcast(Component.text("カウントダウンを開始します。"));
  }

  /**
   * カウントダウンを停止
   */
  public void stop() {
    Bukkit.broadcast(Component.text("カウントダウンを停止しました。"));
    this.cancel();
  }

  /**
   * 指定間隔ごとに、このメソッドを実行（今回は20ティック=1秒ごと）
   */
  @Override
  public void run() {
    // 0以下になったとき(0より小さくなることはないはずだが、なんらかの状況でそうなったとしても止まるようにしている)
    if (seconds <= 0) {
      // メッセージを送る
      Bukkit.broadcast(Component.text("終了!"));
      // cancel()を実行すると停止
      this.cancel();
      // これより下は実行しないようにreturnを返して終わる
      return;
    }

    // 5秒ごと(今の秒数を5で割った余りが0のとき)
    // または5秒未満のときに残り時間を表示
    if (seconds % 5 == 0 || seconds < 5) {
      Bukkit.broadcast(Component.text("残り" + seconds + "秒。"));
    }

    // 1ずつ減らす
    seconds--;
  }

}
