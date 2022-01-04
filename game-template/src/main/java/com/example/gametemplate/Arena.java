package com.example.gametemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * ゲームが行われる会場(arena/闘技場)に関するクラス
 *
 * ここでは会場に関する情報だけを書く
 *
 * その会場でどういうゲームが行われるかはまた別のクラスに書く
 * 例えば、チーム戦が行われるのか、バトルロイヤルが行われるのか、
 * それともただの練習場なのかの違いを別のクラスに分けられる
 */
public class Arena {

  /** この会場の初期スポーンポイント(テレポート位置) */
  private Location spawn = Config.getArenaSpawn();
  /** この会場に所属しているプレイヤー */
  private ArrayList<UUID> players = new ArrayList<>();
  /** ゲームの状態 */
  private GameState state = GameState.RECRUITING;
  /** カウントダウン */
  private Countdown countdown = new Countdown(this);
  /** ゲームルール */
  private GameRule game = new GameRule(this);

  /**
   * ゲーム開始処理
   */
  public void start() {
    setState(GameState.LIVE);
    // 所属しているプレイヤーに通知
    sendMessage(ChatColor.GREEN + "ゲーム開始");
    // ゲーム開始
    game.start();
  }

  /**
   * ゲーム終了処理
   */
  public void reset() {
    // プレイヤーをロビーに移動させる
    for (UUID uuid: players) {
      GameManager.getLobby().teleportLobby(Bukkit.getPlayer(uuid));
    }

    // ゲームの状態を募集中に
    state = GameState.RECRUITING;
    // 登録プレイヤーを初期化
    players.clear();
    // カウントダウンを初期化
    countdown = new Countdown(this);
    // ゲーム状態を初期化
    game = new GameRule(this);
  }

  /**
   * この会場に所属しているプレイヤーにだけメッセージを通知
   *
   * @param message
   */
  public void sendMessage(String message) {
    for (UUID uuid: players) {
      Bukkit.getPlayer(uuid).sendMessage(message);
    }
  }

  /**
   * 初期状態を設定する
   *
   * @param player 対象プレイヤー
   */
  private void initializePlayer(Player player) {
    player.getInventory().clear();
    player.setGameMode(GameMode.ADVENTURE);
  }

  /**
   * 会場が参加者募集中かどうか
   *
   * @return 参加可能判定
   */
  public boolean isRecruiting() {
    return this.state == GameState.RECRUITING;
  }

  /**
   * この会場にプレイヤーを所属させる
   *
   * @param player
   */
  public void addPlayer(Player player) {
    // 参加リストにプレイヤーを登録する
    players.add(player.getUniqueId());
    // プレイヤーの状態を初期化
    initializePlayer(player);
    // プレイヤーを指定位置へテレポート
    player.teleport(spawn);

    // 必要人数を満たしたらカウントダウン開始
    if (players.size() >= Config.getRequiredPlayers()) {
      countdown.begin();
    }
  }

  /**
   * この会場に所属しているプレイヤーを離脱させる
   *
   * @param player
   */
  public void removePlayer(Player player) {
    players.remove(player.getUniqueId());
    GameManager.getLobby().teleportLobby(player);

    // カウントダウン中に必要人数を下回ったらゲーム停止
    if (players.size() <= Config.getRequiredPlayers() && state.equals(GameState.COUNTDOWN)) {
      reset();
    }
    // 誰もいなくなったらゲーム停止(ゲーム中は必要人数を下回ってもゲームは続く)
    if (players.size() == 0 && state.equals(GameState.LIVE)) {
      reset();
    }
  }

  // getter

  public GameRule getGame() {
    return game;
  }

  public List<UUID> getPlayers() {
    return players;
  }

  public GameState getGameState() {
    return state;
  }

  // setter

  public void setState(GameState state) {
    this.state = state;
  }

}
