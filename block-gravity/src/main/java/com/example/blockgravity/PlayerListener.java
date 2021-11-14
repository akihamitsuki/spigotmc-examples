package com.example.blockgravity;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {

  /**
   * プレイヤーがブロックを置いたとき
   *
   * @param event
   */
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    // 設置したブロックの情報
    Block placedBlock = event.getBlock();
    // 置いたブロックが固定ブロックならば
    if (BlockGravity.isFixedBlocks(placedBlock.getType())) {
      // なにもせずに処理を終了(ブロックは通常通り設置される)
      return;
    }
    // 設置イベント自体を無効に
    event.setCancelled(true);
    // 設置したブロックを落下ブロックに変更する
    BlockGravity.replaceFallingBlock(placedBlock);
  }

  /**
   * プレイヤーがブロックを壊したとき
   *
   * @param event
   */
  @EventHandler
  public void onBreakBlock(BlockBreakEvent event) {
    // 上にあるブロックを連続的に落とす
    // 破壊した時点のティック内ですべて処理できないので、`runnable`で周期的に実行する。
    new BlockGravityTask(event.getBlock());
  }

}
