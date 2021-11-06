package com.example.guiinventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

  private Menu menu;

  public MenuListener(Main main) {
    this.menu = main.menu;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    // クリックしたプレイヤーを取得
    Player player = (Player) event.getWhoClicked();
    // インベントリ名はgetView()から取得する
    String inventoryName = event.getView().getTitle();
    // 対象インベントリであれば
    if (inventoryName.contains(menu.equipmentUIName)) {
      // アイテムをクリックしていれば
      if (event.getCurrentItem() != null) {
        // 本来のクリックイベントをキャンセル
        event.setCancelled(true);
        // アイテムの種類に合わせて分岐
        switch (event.getCurrentItem().getType()) {
          case DIAMOND:
            this.menu.equipDiamondArmor(player);
            break;
          case ELYTRA:
            this.menu.equipElytra(player);
            break;
          default:
            return;
        }
      }
    }
  }

}
