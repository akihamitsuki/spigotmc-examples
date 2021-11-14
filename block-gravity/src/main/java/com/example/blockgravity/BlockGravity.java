package com.example.blockgravity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class BlockGravity {

  /** 固定ブロック(このブロックなら落下させない) */
  private static List<Material> fixedBlocks = new ArrayList<Material>(Arrays.asList(
    Material.AIR,
    Material.WATER,
    Material.LAVA,
    Material.STONE
  ));

  /**
   * 対象ブロックが固定ブロックであるかを判定する
   */
  public static boolean isFixedBlocks(Material blockType) {
    return fixedBlocks.contains(blockType);
  }

  /**
   * 対象ブロックを落下ブロックにする
   *
   * @param block
   */
  public static void replaceFallingBlock(Block block) {
    // 空気ブロックにする前に元のデータを残しておく
    BlockData blockData = block.getBlockData();
    // 落ちるブロックはエンティティなので、水平座標がブロックの中心になるように補正する
    Location location = block.getLocation().add(0.5, 0, 0.5);

    // 空気ブロックに変更して削除
    block.setType(Material.AIR);
    // その場所に同じデータで落ちるブロックを呼び出す
    block.getWorld().spawnFallingBlock(location, blockData);
  }

}
