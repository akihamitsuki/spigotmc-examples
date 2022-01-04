package com.example.gametemplate;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {

  private static Main main;

  public Config(Main main) {
    Config.main = main;
    main.getConfig().options().copyDefaults();
    main.saveDefaultConfig();
  }

  /**
   * 必要人数を設定ファイルから取得する
   *
   * @return 必要人数
   */
  public static int getRequiredPlayers() {
    return main.getConfig().getInt("required-players");
  }

  /**
   * カウントダウンの秒数を設定ファイルから取得する
   *
   * @return 秒数
   */
  public static int getCountdownSeconds() {
    return main.getConfig().getInt("countdown-seconds");
  }

  /**
   * ロビーのスポーン位置を設定ファイルから取得する
   *
   * @return ロビーの座標
   */
  public static Location getLobbySpawn() {
    return new Location(
        Bukkit.getWorld(main.getConfig().getString("lobby.world")),
        main.getConfig().getDouble("lobby.x"),
        main.getConfig().getDouble("lobby.y"),
        main.getConfig().getDouble("lobby.z"),
        main.getConfig().getInt("lobby.yaw"),
        main.getConfig().getInt("lobby.pitch"));
  }

  /**
   * 会場のスポーン位置を設定ファイルから取得する
   *
   * @return ロビーの座標
   */
  public static Location getArenaSpawn() {
    return new Location(
        Bukkit.getWorld(main.getConfig().getString("arena.world")),
        main.getConfig().getDouble("arena.x"),
        main.getConfig().getDouble("arena.y"),
        main.getConfig().getDouble("arena.z"),
        main.getConfig().getInt("arena.yaw"),
        main.getConfig().getInt("arena.pitch"));
  }

}
