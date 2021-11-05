package com.example.countdown;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private static Main instance;

  @Override
  public void onEnable() {
    Main.instance = this;

    this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
  }

  /**
   * 他クラスからJavaPluginのインスタンスを取得しやすいようにするメソッド
   */
  public static Main getInstance() {
    return instance;
  }

}
