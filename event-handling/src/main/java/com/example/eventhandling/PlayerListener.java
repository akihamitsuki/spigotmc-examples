package com.example.eventhandling;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

  /**
   * プレイヤーが歩いた時のイベント
   *
   * @param event
   */
  @EventHandler
  // メソッド名はなんでも良い。引数の型でいつ検知されるのかが変わる
  public void onPlayerMove(PlayerMoveEvent event) {
    // event変数からプレイヤーを取得
    Player player = event.getPlayer();
    // プレイヤーの位置を取得
    Location location = player.getLocation();
    // 足元のブロックを取得(その位置からy座標をマイナス1)
    Block block = location.add(0, -1, 0).getBlock();
    // そのブロックが空気ブロックでなければ
    if (block.getType() != Material.AIR) {
      // 赤の羊毛に変える
      block.setType(Material.RED_WOOL);
    }
  }
}
