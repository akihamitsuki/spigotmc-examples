package com.example.teamclassification;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PlayerListener implements Listener {

  /**
   * GUIインベントリの処理
   *
   * @param event
   */
  @EventHandler
  public void onInventryClick(InventoryClickEvent event) {
    // インベントリ内でクリックしたプレイヤー
    Player player = (Player) event.getWhoClicked();
    // インベントリのタイトルで対象のGUIかどうかを判定している。またアイテムをクリックをしているか
    if (event.getView().title().toString().contains(Team.getTitle()) && event.getCurrentItem() != null) {
      TeamGui.onInventryClick(event);
      // インベントリのイベントを停止
      event.setCancelled(true);
      // インベントリを閉じる
      player.closeInventory();
    }
  }

  /**
   * チームに入っていた場合の処理例
   *
   * @param event
   */
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    // プレイヤーを取得
    Player player = event.getPlayer();
    // プレイヤーの所属するチームを取得
    Team team = Main.getInstance().getTeamManager().getTeam(player);
    // チームに所属していれば
    if (team != null) {
      // ここで何かする
      player.sendMessage(Component.text(team.getName(), team.getTextColor())
          .append(Component.text("のプレイヤーがブロックを壊しました。", NamedTextColor.WHITE)));
    }
  }
}
