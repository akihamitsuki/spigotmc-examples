package com.example.statscomponent;

import org.bukkit.Bukkit;

/**
 * ゲーム全体を管理(manage)するクラス
 */
public class GameManager {

  private StatsManager statsManager;

  public GameManager(Main main) {
    // 統計情報管理インスタンスを生成する
    this.statsManager = new StatsManager();
    // 統計情報リスナーを登録する
    Bukkit.getPluginManager().registerEvents(new StatsListener(this), main);
  }

  public StatsManager getStatsManager() {
    return this.statsManager;
  }

}
