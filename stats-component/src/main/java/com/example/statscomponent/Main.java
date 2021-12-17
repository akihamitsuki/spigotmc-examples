package com.example.statscomponent;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * メインクラス
 *
 * プラグインをどう扱うかに関する記述をする
 */
public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    // ゲーム管理クラスを起動する
    new GameManager(this);
  }

}
