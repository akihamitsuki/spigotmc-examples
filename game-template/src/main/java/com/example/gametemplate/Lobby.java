package com.example.gametemplate;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * ロビー(lobby)に関するクラス
 */
public class Lobby {

  public void teleportLobby(Player player) {
    // プレイヤーを初期化する
    initializePlayer(player);
    // プレイヤーをロビーに移動させる
    player.teleport(Config.getLobbySpawn());
  }

  /**
   * ロビーにおける初期状態を設定する
   *
   * @param player 対象プレイヤー
   */
  private void initializePlayer(Player player) {
    player.getInventory().clear();
    player.setGameMode(GameMode.ADVENTURE);
  }

}
