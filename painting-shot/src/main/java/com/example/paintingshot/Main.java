package com.example.paintingshot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private static Main instance;

  @Override
  public void onEnable() {
    Main.instance = this;
    Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
  }

  /**
   * Main(Plugin)インスタンスを取得する
   *
   * @return Mainインスタンス
   */
  public static Main getInstance() {
    return instance;
  }

}
