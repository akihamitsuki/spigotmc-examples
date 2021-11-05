package com.example.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

  private CountDownTimer countDownTimer;

  public PlayerListener() {
    countDownTimer = null;
  }

  /**
   * アイテムを使ってカウントダウン機能を操作
   *
   * @param event
   */
  @EventHandler
  public void onPlayerUseItem(PlayerInteractEvent event) {
    // アイテムを使ったプレイヤーを取得
    Player player = event.getPlayer();
    // そのプレイヤーが利き手(main hand)に持っているアイテムを取得
    Material item = player.getInventory().getItemInMainHand().getType();

    // そのアイテムが時計ならば、カウントダウン開始
    if (item.equals(Material.CLOCK)) {
      // インスタンスを作成していれば停止処理を入れて重複を避ける
      if (countDownTimer != null) {
        countDownTimer.cancel();
      }
      // インスタンスを作成
      countDownTimer = new CountDownTimer();
      // カウントダウンを開始
      countDownTimer.start();
    }
    // そのアイテムが羽根ならば、カウントダウン停止
    if (item.equals(Material.FEATHER)) {
      // インスタンスを作成していなければ停止処理をしないように
      if (countDownTimer != null) {
        countDownTimer.stop();
      }
    }
  }

}
