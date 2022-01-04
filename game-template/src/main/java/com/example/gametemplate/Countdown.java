package com.example.gametemplate;

import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.ChatColor;

public class Countdown extends BukkitRunnable {

  private Arena arena;
  private int seconds;

  public Countdown(Arena arena) {
    this.arena = arena;
    // 設定ファイルから秒数を取得する
    this.seconds = Config.getCountdownSeconds();
  }

  /**
   * タイマーを開始する
   */
  public void begin() {
    // 会場の状態をカウントダウンに設定する
    arena.setState(GameState.COUNTDOWN);
    // 0ティック後から、20ティックごとにrun()を実行する
    this.runTaskTimer(Main.getInstance(), 0, 20);
  }

  /**
   * 指定ティック間隔で実行され続ける
   */
  @Override
  public void run() {
    // 0秒以下になれば(設定次第では0より小さくなる可能性がある)
    if (seconds <= 0) {
      // このタイマーを停止させる
      cancel();
      // 会場のゲームを開始する
      arena.start();
      // リターンでこのメソッドを終了させる
      return;
    }

    // 10秒ごと、または5秒以下のときに通知する
    if (seconds % 10 == 0 || seconds <= 5) {
      arena.sendMessage(ChatColor.AQUA + "ゲームが " + seconds + " 秒後に始まります。");
    }

    // カウントダウン中に必要人数を下回った場合の処理
    if (arena.getPlayers().size() < Config.getRequiredPlayers()) {
      // このタイマーを停止させる
      cancel();
      // 会場の状態を募集中に設定する
      arena.setState(GameState.RECRUITING);
      // 会場のプレイヤーに通知する。これは抜けたプレイヤーには通知されない。
      arena.sendMessage(ChatColor.RED + "開始条件を満たさなくなったのでカウントダウンを停止します。");
      // リターンでこのメソッドを終了させる
      return;
    }

    // 秒数を-1する
    seconds--;
  }

}
