package com.example.configfile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Config {

  private static Main main;
  private static FileConfiguration config;

  public Config(Main main) {
    // 設定を操作するメソッドはJavaPlugin(Main)にある
    Config.main = main;
    // 設定の取得
    Config.config = main.getConfig();
    // 初期値をconfig.ymlファイルから取得する
    config.options().copyDefaults(true);
  }

  /**
   * 設定をファイルに保存する
   */
  public static void save() {
    main.saveConfig();
  }

  /**
   * ゾンビのスポーン位置を取得する
   *
   * @return
   */
  public static Location getZombieSpawnLocation() {
    // getStringならString型で取得
    // getDoubleならDouble型で取得
    return new Location(
        Bukkit.getWorld(config.getString("zombie.spawn.world")),
        config.getDouble("zombie.spawn.x"),
        config.getDouble("zombie.spawn.y"),
        config.getDouble("zombie.spawn.z"));
  }

  /**
   * ゾンビのスポーン位置を設定する
   *
   * @param player
   */
  public static void setZombieSpawnLocation(Player player) {
    Location location = player.getLocation();
    // この時点では読み来れている設定データが更新されるだけで、ファイルには保存されない
    config.set("zombie.spawn.world", player.getWorld().getName());
    config.set("zombie.spawn.x", location.getX());
    config.set("zombie.spawn.y", location.getY());
    config.set("zombie.spawn.z", location.getZ());
  }

}
