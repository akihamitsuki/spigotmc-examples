package com.example.signboard;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Signboard {

  /** 改行の間隔(単位はブロック) */
  private Double lineHeight = 0.3;

  /**
   * 文章を表示する
   *
   * @param world 対象ワールド
   * @param location 対象座標
   * @param lines 配列に直した文章
   */
  public void showText(World world, Location location, String[] lines) {
    // 最初の高さの計算(行数の分だけ高くする)
    // 実際に文字が表示される個所は防具立ての頭の上なので、必要があればさらに補正をかけること
    Double y = (lines.length - 1) * lineHeight;
    location.add(0, y, 0);
    // 表示する文字の配列で繰り返し
    for (int i = 0; i < lines.length; i++) {
      // 防具立ての配置箇所
      location.add(0, -lineHeight, 0);
      // 表示用の防具立てを表示
      this.spawnArmorStand(world, location, lines[i]);
    }
  }

  /**
   * 文字を表示する防具立てをスポーンさせる
   *
   * @param world 対象ワールド
   * @param location 対象座標
   * @param name 表示する文字
   */
  public void spawnArmorStand(World world, Location location, String name) {
    // 防具立てをスポーンさせる
    ArmorStand stand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
    // 表示するか
    stand.setVisible(false);
    // 重力の影響を受けるか
    stand.setGravity(false);
    // 不死身であるか
    // invulnerable: 傷つくことのない、不死身の、難攻不落の
    stand.setInvulnerable(true);
    // カスタムネームを表示するか
    stand.setCustomNameVisible(true);
    // カスタムネームの設定
    stand.setCustomName(name);
  }

}
