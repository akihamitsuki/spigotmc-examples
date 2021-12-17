package com.example.statscomponent;

/**
 * 各プレイヤーの統計情報を保持するクラス
 */
public class StatsComponent {

  /** 倒した数 */
  private Integer kill = 0;
  // private Integer death = 0;
  // private Integer brokenBlock = 0;
  // ...必要な情報を追記していく

  /**
   * 倒した数を加算する
   */
  public void addKillCount() {
    this.kill += 1;
  }

  /**
   * 倒した数を取得する
   *
   * @return 倒した数
   */
  public Integer getKillCount() {
    return this.kill;
  }

  // 以降同じように必要なメソッドを加える

}
