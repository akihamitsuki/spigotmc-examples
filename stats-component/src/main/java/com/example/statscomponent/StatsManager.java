package com.example.statscomponent;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

/**
 * 統計情報を管理する
 */
public class StatsManager {

  /** プレイヤーとコンポーネントとの対応関係 */
  private HashMap<UUID, StatsComponent> scores = new HashMap<>();

  /**
   * 統計コンポーネントを作成する
   *
   * @param player 対象プレイヤー
   * @return 作成されたコンポーネント
   */
  public StatsComponent createScoreComponent(Player player) {
    StatsComponent component = new StatsComponent();
    scores.put(player.getUniqueId(), component);
    return component;
  }

  /**
   * 統計コンポーネントを取得する
   *
   * @param player 対象プレイヤー
   * @return 取得されたコンポーネント
   */
  public StatsComponent getScoreComponent(Player player) {
    // 登録ハッシュマップから探す
    StatsComponent component = scores.get(player.getUniqueId());
    if (component != null) {
      // 存在すれば、そのコンポーネントを返す
      return component;
    }
    // なければ作成して、そのコンポーネントを返す
    return createScoreComponent(player);
  }

  /**
   * 倒した数を追加する
   *
   * @param player 対象プレイヤー
   */
  public void addKillCount(Player player) {
    // 対象プレイヤーの統計コンポーネントを取得する
    StatsComponent component = getScoreComponent(player);
    // そのコンポーネントの加算メソッドを使う
    component.addKillCount();
    // プレイヤーに通知をする
    player.sendMessage(component.getKillCount() + "体倒ししました。");
  }

}
