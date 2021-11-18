package com.example.countdown;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  /** インスタンスを残しておくための変数 */
  private static Main instance;

  @Override
  public void onEnable() {
    // プラグインが有効になった時のインスタンスを残す
    Main.instance = this;

    this.getCommand("countdown").setExecutor(new CommandCountdown());
  }

  /**
   * 他クラスからJavaPluginのインスタンスを取得しやすいようにしている
   */
  public static Main getInstance() {
    return instance;
  }

}
