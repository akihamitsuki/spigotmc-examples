package com.example.displaytext;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Text {

  /**
   * 文字を表示する防具立てをスポーンさせる
   *
   * @param world 対象ワールド
   * @param location 対象座標
   * @param name 表示する文字
   */
  public static ArmorStand spawnArmorStand(World world, Location location, String name) {
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

    return stand;
  }

}
