package com.example.gametemplate;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * ゲームルールに関するクラス
 */
public class GameRule {

  /** ゲームが所属する会場 */
  private Arena arena;
  /** 各プレイヤーの得点を保持するハッシュマップ */
  private HashMap<UUID, Integer> points = new HashMap<>();

  /**
   * コンストラクタ
   *
   * @param arena
   */
  public GameRule(Arena arena) {
    this.arena = arena;
    // ゲーム用のイベントリスナーを登録する
    Bukkit.getPluginManager().registerEvents(new GameRuleListener(), Main.getInstance());
  }

  /**
   * ゲーム開始処理
   */
  public void start() {
    // 参加プレイヤー(だけ)をゲーム用に初期化
    for (UUID uuid: arena.getPlayers()) {
      playerInitialize(uuid);
    }
    arena.sendMessage(ChatColor.GOLD + "20ブロック壊せ!");
  }

  /**
   * プレイヤーをこのゲーム用に初期化する
   *
   * @param uuid
   */
  private void playerInitialize(UUID uuid) {
    Player player = Bukkit.getPlayer(uuid);
    // 得点を0にする
    points.put(uuid, 0);
    // 持ち物を削除する
    player.getInventory().clear();
    // インベントリを開いていたら閉じる
    player.closeInventory();
    // ゲームモードをサバイバルにする
    player.setGameMode(GameMode.SURVIVAL);
    // エフェクトをすべて消す
    for (PotionEffect effect : player.getActivePotionEffects()) {
      player.removePotionEffect(effect.getType());
    }
  }

  /**
   * ポイント加算処理
   *
   * @param player 対象プレイヤー
   */
  public void addPoint(Player player, Integer point) {
    // 合計ポイントを取得
    int total = points.get(player.getUniqueId()) + point;
    // 得点を通知
    player.sendMessage(ChatColor.GOLD + "+" + point + "点獲得。現在" + total + "点");
    // 合計ポイントで上書き
    points.replace(player.getUniqueId(), total);

    // 終了判定を行う
    if (isGameOver(total)) {
      gameOver();
    }
  }

  /**
   * ゲーム終了判定
   *
   * @param point
   * @return
   */
  private boolean isGameOver(Integer point) {
    return point >= 20;
  }

  /**
   * 最大スコアを持つプレイヤーを探す
   *
   * @return
   */
  private Player getHighestScorePlayer() {
    // 各プレイヤーのスコアから最大の値を探す
    // 初期値は0
    int max = 0;
    // ポイントのハッシュマップで繰り返し
    for(Entry<UUID, Integer> entry : points.entrySet()) {
      // 2つを比べて大きい方を選び、変数に入れる
      max = Math.max(max, entry.getValue());
    }

    // その最大スコアを持つプレイヤーを探す
    // NOTE: この場合は同点のときに最初の一人しか返さないので注意
    for(Entry<UUID, Integer> entry : points.entrySet()) {
      // そのポイントが上で調べた最大値と等しければ
      if(entry.getValue() == max) {
        // そのポイントを持っているプレイヤーを返す
        return Bukkit.getPlayer(entry.getKey());
      }
    }
    // 誰も該当しなければnullで返す
    return null;
  }

  /**
   * ゲーム終了処理
   */
  private void gameOver() {
    // 勝利者の通知
    Player winner = this.getHighestScorePlayer();
    if (winner != null) {
      arena.sendMessage(ChatColor.GOLD + winner.getName() + "の勝ちです。");
    } else {
      // 勝者がいなければ引き分け判定
      arena.sendMessage(ChatColor.GOLD + "引き分けです。");
    }
    // ゲームをリセット
    arena.reset();
  }
}
