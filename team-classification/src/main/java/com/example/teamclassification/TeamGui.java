package com.example.teamclassification;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamGui {

  public TeamGui(Player player) {
    // GUIとして扱うインベントリを作成
    Inventory gui = Bukkit.createInventory(null, 9, Team.getTitle());

    for (Team team : Team.values()) {
      // アイテムを作成
      ItemStack itemStack = new ItemStack(team.getMaterial());
      // アイテムのメタデータを作成
      ItemMeta itemMeta = itemStack.getItemMeta();
      // アイテムの名前を表示
      itemMeta.setDisplayName(team.getName());
      //
      itemMeta.setLocalizedName(team.name());
      // アイテムにメタデータを設定
      itemStack.setItemMeta(itemMeta);
      // GUI(インベントリ)にアイテムを設定
      gui.addItem(itemStack);
    }
    // 一覧表示・離脱
    gui.setItem(7, getListItem());
    gui.setItem(8, getRemoveItem());
    // GUIインベントリを開く
    player.openInventory(gui);
  }

  /**
   * 一覧表示用のアイテムを作成
   *
   * @return
   */
  private ItemStack getListItem() {
    ItemStack itemStack = new ItemStack(Material.PAPER);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("チーム一覧を表示");
    itemMeta.setLocalizedName("list");
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  /**
   * 離脱用のアイテムを作成
   *
   * @return
   */
  private ItemStack getRemoveItem() {
    ItemStack itemStack = new ItemStack(Material.TNT);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("チームから外れる");
    itemMeta.setLocalizedName("remove");
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  /**
   * GUIから操作が行われたとき
   *
   * @param event
   */
  public static void onInventryClick(InventoryClickEvent event) {
    // インベントリ内でクリックしたプレイヤー
    Player player = (Player) event.getWhoClicked();
    // クリックしたアイテム名
    String itemName = event.getCurrentItem().getItemMeta().getLocalizedName();
    // アイテム名で分岐
    if (itemName.equalsIgnoreCase("remove")) {
      TeamGui.removeTeam(player);
    } else if (itemName.equalsIgnoreCase("list")) {
      TeamGui.listTeam(player);
    } else {
      TeamGui.joinTeam(player, itemName);
    }
  }

  /**
   * プレイヤーをチームに所属させる
   *
   * @param player
   * @param itemName
   */
  public static void joinTeam(Player player, String itemName) {
    // クリックしたアイテムからチームを取得する
    Team team = Team.valueOf(itemName);
    // チームを操作するマネージャーを取得する
    TeamManager teamManager = Main.getInstance().getTeamManager();
    // 未所属、かつクリックしたチームに所属していなければ
    if (teamManager.getTeam(player) == null || !teamManager.getTeam(player).equals(team)) {
      // 通知
      player.sendMessage(team.getName() + ChatColor.RESET + "に入りました。");
      // チームに所属させる
      teamManager.setTeam(player, team);
    } else {
      // 通知
      player.sendMessage("すでに" + team.getName() + ChatColor.RESET + "に入っています。");
    }
  }

  /**
   * チームから外れる
   *
   * @param player
   */
  public static void removeTeam(Player player) {
    boolean isRemoved = Main.getInstance().getTeamManager().removeTeam(player);
    if (isRemoved) {
      player.sendMessage(ChatColor.GREEN + "チームから外れました");
    } else {
      player.sendMessage(ChatColor.YELLOW + "チームに所属していません");
    }
  }

  /**
   * チーム一覧を表示する
   *
   * @param player
   */
  public static void listTeam(Player player) {
    TeamManager teamManager = Main.getInstance().getTeamManager();
    for (Team team : Team.values()) {
      player.sendMessage(
          team.getName()
          + ChatColor.RESET + "には"
          + ChatColor.BOLD + teamManager.getTeamCount(team)
          + ChatColor.RESET + "人が所属しています。");
    }
  }

}
