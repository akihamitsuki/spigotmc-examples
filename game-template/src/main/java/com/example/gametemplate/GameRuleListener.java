package com.example.gametemplate;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameRuleListener implements Listener {

  /**
   * プレイヤーがブロックを壊したとき
   *
   * @param event
   */
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    // そのプレイヤーはゲーム中か
    boolean isPlaying = GameManager.isPlaying(player);
    // そのプレイヤーのいる会場はゲーム中か
    boolean isGameLive = GameManager.getArena().getGameState().equals(GameState.LIVE);
    // 両方を満たしていたら
    if (isPlaying && isGameLive) {
      // ポイントの加算処理を行う(具体的な処理はゲームクラスで行う)
      GameManager.getArena().getGame().addPoint(player, 1);
    }
  }

  /**
   * プレイヤーがログアウトしたとき
   *
   * @param event
   */
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    // ログアウトしたプレイヤーを取得
    Player player = event.getPlayer();
    // そのプレイヤーがゲーム中なら
    if (GameManager.isPlaying(player)) {
      // その情報を削除する
      GameManager.getArena().removePlayer(player);
    }
  }
}
