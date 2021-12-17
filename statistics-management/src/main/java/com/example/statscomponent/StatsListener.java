package com.example.statscomponent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * 統計データを取る条件
 */
public class StatsListener implements Listener {

  private StatsManager statsManager;

  public StatsListener(GameManager gameManager) {
    this.statsManager = gameManager.getStatsManager();
  }

  /**
   * プレイヤーがエンティティを倒したときに、倒した数を追加する
   *
   * @param event
   */
  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {
    // 倒したプレイヤーを取得
    Player player = event.getEntity().getKiller();
    // 統計情報管理インスタンスのメソッドを起動する
    this.statsManager.addKillCount(player);
  }

}
