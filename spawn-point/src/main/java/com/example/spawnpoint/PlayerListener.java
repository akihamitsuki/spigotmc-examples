package com.example.spawnpoint;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * プレイヤーのイベントを管理するクラス
 */
public class PlayerListener implements Listener {

  // PlayerManager型は自分で作成したクラスのこと
  // 同じパッケージ内にあるので、特に読み込みは行わなくても使える
  private PlayerManager playerManager;

  /**
   * コンストラクタ
   *
   * @param playerManager
   */
  public PlayerListener(PlayerManager playerManager) {
    // プレイヤー管理インスタンスを受け取り、このクラス内の変数に格納する
    // (PlayerManagerクラスの中でPlayerListenerに渡したthisのこと)
    // このクラスの変数に入れておくことで、このクラス内で扱うことができるようになる
    this.playerManager = playerManager;
  }

  /**
   * プレイヤーがログインしたとき
   *
   * @param event
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // イベント情報からログインしてきたプレイヤーを取得
    Player player = event.getPlayer();
    // プレイヤー管理インスタンスのlobby変数に値があれば(nullでなければ)
    if (playerManager.lobby != null) {
      // 対象の座標にプレイヤーを移動させる
      player.teleport(playerManager.lobby);
    }
    // ロビー座標がなければ何もしない
  }

}
