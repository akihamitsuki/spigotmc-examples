package com.example.spawnpoint;

import org.bukkit.Location;

/**
 * プレイヤーを管理するクラス
 */
public class PlayerManager {

  // このクラスにロビー座標（ログイン時のスポーンポイント）の変数を持たせる
  Location lobby = null;

  /**
   * コンストラクタ
   * インスタンス作成時に最初に実行されるメソッド
   *
   * @param main
   */
  public PlayerManager(Main main) {
    // プレイヤーに関するイベントを登録する
    // 引数としてthis(PlayerManagerクラスのインスタンス)を渡している
    // mainはMainクラスのインスタンス。これはイベントの登録先のこと
    main.getServer().getPluginManager().registerEvents(new PlayerListener(this), main);
    // プレイヤーに関するコマンドを登録する
    // 上と同じように引数としてthisを渡している
    // plugin.ymlにもコマンドを登録するのを忘れないように
    main.getCommand("player").setExecutor(new PlayerCommand(this));
  }
}
