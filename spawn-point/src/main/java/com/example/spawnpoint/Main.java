package com.example.spawnpoint;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * プラグイン全体を管理するクラス
 */
public class Main extends JavaPlugin {

  /**
   * プラグインが有効(enable)になったときに起動するメソッド
   */
  @Override
  public void onEnable() {
    // プレイヤー管理クラスの起動する（インスタンスを作成する）
    // 「new クラス名」でそのクラスからインスタンスを作成する操作
    // thisはMainクラスのインスタンス
    new PlayerManager(this);
    // クラスは「タイ焼きの金型」
    // インスタンスは「タイ焼き」
    // 参考書などで、クラスとインスタンスに関する記述をよく読むこと
  }

}
