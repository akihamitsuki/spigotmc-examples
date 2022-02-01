package com.example.guiinventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.kyori.adventure.text.Component;

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
    Component inventoryName = event.getView().title();
    // 対象インベントリであれば
    if (inventoryName.contains(menu.equipmentUIName)) {
      // アイテムをクリックしていれば
      if (event.getCurrentItem() != null) {
        // 本来のクリックイベントをキャンセル
        event.setCancelled(true);
        // アイテムの種類に合わせて分岐
        // NOTE: この場合、自分のインベントリに同じアイテムがあれば、それにも反応する
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
