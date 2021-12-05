package com.example.teamclassification;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamManager {

  /** プレイヤーのチーム状態を格納する */
  private HashMap<UUID, Team> teams = new HashMap<>();

  /**
   * そのプレイヤーのチームを取得する
   *
   * @param player 対象プレイヤー
   * @return チーム
   */
  public Team getTeam(Player player) {
    return teams.get(player.getUniqueId());
  }

  /**
   * そのプレイヤーをチームに所属させる
   *
   * @param player 対象プレイヤー
   * @param team 対象チーム
   */
  public void setTeam(Player player, Team team) {
    // すでに所属していれば解除する
    removeTeam(player);
    // ハッシュマップにプレイヤーと対応するチームを入れる
    teams.put(player.getUniqueId(), team);
  }

  /**
   * そのプレイヤーをチームから外す
   *
   * @param player 対象プレイヤー
   * @return 所属していた上で外れたか
   */
  public boolean removeTeam(Player player) {
    // ハッシュマップにユーザーの情報があるか
    if (teams.containsKey(player.getUniqueId())) {
      // ハッシュマップから削除する
      teams.remove(player.getUniqueId());
      // 削除成功判定
      return true;
    }
    // そもそも所属していない、という情報を返す
    return false;
  }

  /**
   * そのチームに所属している人数を取得する
   *
   * @param team 対象チーム
   * @return 人数
   */
  public int getTeamCount(Team team) {
    int amount = 0;
    for (Team t : teams.values()) {
      if (t.equals(team)) {
        amount++;
      }
    }

    return amount;
  }

}
