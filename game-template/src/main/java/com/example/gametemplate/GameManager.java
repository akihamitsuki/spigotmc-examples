package com.example.gametemplate;

import org.bukkit.entity.Player;

/**
 * ゲーム全体を管理(manage)するクラス
 *
 * 直接的には会場(arena)を管理する
 * ゲーム内容は各会場に所属する
 *
 * ここでは会場は一つだけだが、ゲーム次第では複数存在することがあり得る
 */
public class GameManager {

  private static Arena arena;
  private static Lobby lobby;

  public GameManager() {
    arena = new Arena();
    lobby = new Lobby();
  }

  /**
   * そのプレイヤーがゲームに参加中か
   *
   * @param player 対象プレイヤー
   * @return 参加中かどうか
   */
  public static boolean isPlaying(Player player) {
    if (arena.getPlayers().contains(player.getUniqueId())) {
      return true;
    }

    return false;
  }

  /**
   * そのプレイヤーの参加しているアリーナ
   *
   * @param player 対象プレイヤー
   * @return 参加しているアリーナのインスタンス
   */
  public static Arena getArena(Player player) {
    if (arena.getPlayers().contains(player.getUniqueId())) {
      return arena;
    }

    return null;
  }

  // getter

  public static Arena getArena() {
    return arena;
  }

  public static Lobby getLobby() {
    return lobby;
  }

}
