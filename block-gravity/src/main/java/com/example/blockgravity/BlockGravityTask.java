package com.example.blockgravity;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockGravityTask  extends BukkitRunnable {

  private Block block;

  /**
   * コンストラクタ
   *
   * @param brokenBlock 破壊されたブロック情報
   */
  public BlockGravityTask(Block brokenBlock) {
    this.block = brokenBlock;
    this.runTaskTimer(Main.getInstance(), 5, 5);
  }

  /**
   * 指定間隔ごとに、このメソッドを実行
   */
  @Override
  public void run() {
    // 一つ上のブロックを取得
    this.block = this.block.getRelative(BlockFace.UP, 1);
    // そのブロックが固定ブロックならば処理を停止
    if (BlockGravity.isFixedBlocks(block.getType())) {
      this.cancel();
      return;
    }
    // 対象ブロックを落下ブロックにする
    BlockGravity.replaceFallingBlock(block);
  }

}
