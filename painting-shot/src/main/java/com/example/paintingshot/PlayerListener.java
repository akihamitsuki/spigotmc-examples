package com.example.paintingshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class PlayerListener implements Listener {

  // 塗り替える範囲の半径。2の場合は-2,-1,0,1,2の5ブロック
  private static Integer radius = 2;

  // 塗り替え可能なブロック
  private static List<Material> allowPaintingBlocks = new ArrayList<Material>(Arrays.asList(
    Material.GRASS_BLOCK,
    Material.DIRT,
    Material.STONE
  ));

  // ブロックの上下東西南北の向きリスト
  private static List<BlockFace> faces = new ArrayList<BlockFace>(Arrays.asList(
    BlockFace.UP,
    BlockFace.DOWN,
    BlockFace.EAST,
    BlockFace.WEST,
    BlockFace.SOUTH,
    BlockFace.NORTH
  ));

  /**
   * 塗り替え可能判定
   *
   * @param block 対象ブロック
   * @return 判定
   */
  private static boolean canPaintBlock(Block block) {
    // 塗り替え可能ブロックに含まれているか、かつ空気に触れているか
    return allowPaintingBlocks.contains(block.getType()) && isExposedBlock(block);
  }

  /**
   * そのブロックは空気に触れているか
   *
   * 空気に触れていなければ、地中などのブロックなので、塗り替えないようにしたい
   *
   * @param block 対象ブロック
   * @return 判定
   */
  private static boolean isExposedBlock(Block block) {
    // あらかじめ設定しておいた向き配列で繰り返し
    for (BlockFace blockFace : faces) {
      // その向きにあるブロックは空気であるか
      if(block.getRelative(blockFace).getType().equals(Material.AIR)) {
        // 真ならリターンして終了(今回の条件ではどこか1つだけでいい)
        return true;
      }
    }

    return false;
  }

  /**
   * 投げたアイテムが何かに当たった時
   *
   * @param event
   */
  @EventHandler
  public void onProjectileHit(ProjectileHitEvent event) {
    // 当たったブロックを取得
    Block hitBlock = event.getHitBlock();
    if (hitBlock == null) {
      return;
    }

    Projectile projectile = event.getEntity();
    // プレイヤーが投げたものに限定する
    if(projectile.getShooter() instanceof Player){
      Location hitBlockPosition = hitBlock.getLocation();

      // 半径(radius)のプラスマイナス分をxyz座標で繰り返し
      for (int x = -radius; x <= radius; x++) {
        for (int y = -radius; y <= radius; y++) {
          for (int z = -radius; z <= radius; z++) {
            // 基準座標を複製する(ここでクローンを使わないと基準座標自体が変更されてしまう)
            Location targetLocation = hitBlockPosition.clone();
            // 複製した座標に繰り返し部分の数値を加える
            targetLocation.add(x, y, z);
            // その座標のブロックを取得する
            Block targetBlock = targetLocation.getBlock();
            // そのブロックは塗り替え可能なブロックか
            if (canPaintBlock(targetBlock)) {
              // ブロックを置き換える
              targetBlock.setType(Material.RED_WOOL);
            }
          }
        }
      }
    }
  }
}
