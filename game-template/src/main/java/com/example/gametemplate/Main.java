package com.example.gametemplate;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * メインクラス
 *
 * 基本的にはプラグインをどう扱うかの関する記述をする
 */
public class Main extends JavaPlugin {

  private static Main instance;

  @Override
  public void onEnable() {
    // インスタンスをクラス変数に残す
    Main.instance = this;
    // 初期設定を読み込む -> resources/config.yml
    new Config(this);
    // ゲームマネージャーを起動する
    new GameManager();
    // コマンドを登録する
    getCommand("game").setExecutor(new GameCommand());
    // イベントリスナーを登録する
    Bukkit.getPluginManager().registerEvents(new GameRuleListener(), this);
  }

  /**
   * Main(Plugin)インスタンスを取得する
   *
   * これにより、どこからでもMainインスタンスを取得できる
   * この一連の手法は「シングルトン(singleton)」という
   *
   * @return Mainインスタンス
   */
  public static Main getInstance() {
    return instance;
  }

}
