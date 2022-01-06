package com.example.cooldown;

import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerListener implements Listener {

  // ブロック破壊のクールダウン状況を管理する
  public ArrayList<UUID> cooldownBlockBreak = new ArrayList<>();

  /**
   * プレイヤーがブロックを壊したとき
   *
   * @param event
   */
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();

    // 管理用の配列に自分の情報が入っていたらクールダウン中判定
    if (this.cooldownBlockBreak.contains(player.getUniqueId())) {
      // イベントを中止
      event.setCancelled(true);
      player.sendMessage("クールダウン期間が終わるまでブロックは壊せません。");
    } else {
      // 管理用の配列に自分の情報を入れる
      this.cooldownBlockBreak.add(player.getUniqueId());
      // タイマーを起動して、一定時間後に配列内の情報を削除する
      // 処理が短い場合は、別クラスにせずに直接書くこともできる
      Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          // 配列から自分の情報を削除(行動可能に戻す)
          cooldownBlockBreak.remove(player.getUniqueId());
          player.sendMessage("ブロックを破壊できるようになりました。");
        }
      }, 100L); // ここの数字ティック後にタイマーが起動する
    }
  }
}
