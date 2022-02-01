package com.example.teamclassification;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class TeamGui {

  public TeamGui(Player player) {
    Component guiName = Component.text(Team.getTitle()).decoration(TextDecoration.BOLD, true);
    // GUIとして扱うインベントリを作成
    Inventory gui = Bukkit.createInventory(null, 9, guiName);

    for (Team team : Team.values()) {
      // アイテムを作成
      ItemStack itemStack = new ItemStack(team.getMaterial());
      // アイテムのメタデータを作成
      ItemMeta itemMeta = itemStack.getItemMeta();
      // アイテムの名前を表示
      itemMeta.displayName(Component.text(team.getName(), team.getTextColor()));
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

  private static String listName = "チーム一覧を表示";

  /**
   * 一覧表示用のアイテムを作成
   *
   * @return
   */
  private ItemStack getListItem() {
    ItemStack itemStack = new ItemStack(Material.PAPER);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.displayName(Component.text(listName));
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  private static String removeName = "チームから外れる";

  /**
   * 離脱用のアイテムを作成
   *
   * @return
   */
  private ItemStack getRemoveItem() {
    ItemStack itemStack = new ItemStack(Material.TNT);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.displayName(Component.text(removeName));
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
    Component itemName = event.getCurrentItem().getItemMeta().displayName();
    // アイテム名で分岐
    if (itemName.toString().contains(removeName)) {
      TeamGui.removeTeam(player);
    } else if (itemName.toString().contains(listName)) {
      TeamGui.listTeam(player);
    } else {
      TeamGui.joinTeam(player, getTeamByItemName(itemName));
    }
  }

  /**
   * アイテム名からチームを取得する
   *
   * @param itemName
   * @return
   */
  private static Team getTeamByItemName(Component itemName) {
    TextComponent name = (TextComponent) itemName;
    for (Team team : Team.values()) {
      if (team.getName().equals(name.content())) {
        return team;
      }
    }
    return null;
  }

  /**
   * プレイヤーをチームに所属させる
   *
   * @param player
   * @param team
   */
  public static void joinTeam(Player player, Team team) {
    // チームを操作するマネージャーを取得する
    TeamManager teamManager = Main.getInstance().getTeamManager();
    // 未所属、かつクリックしたチームに所属していなければ
    if (teamManager.getTeam(player) == null || !teamManager.getTeam(player).equals(team)) {
      // チームに所属させる
      teamManager.setTeam(player, team);
      // 通知
      player.sendMessage(Component.text(team.getName(), team.getTextColor())
          .append(Component.text("に入りました。", NamedTextColor.WHITE)));
    } else {
      // 通知
      player.sendMessage(Component.text("すでに")
          .append(Component.text(team.getName(), team.getTextColor()))
          .append(Component.text("に入っています。", NamedTextColor.WHITE)));
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
      player.sendMessage(Component.text("チームから外れました", NamedTextColor.GREEN));
    } else {
      player.sendMessage(Component.text("チームに所属していません", NamedTextColor.YELLOW));
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
      player.sendMessage(Component.text(team.getName(), team.getTextColor())
          .append(Component.text("には", NamedTextColor.WHITE))
          .append(Component.text(teamManager.getTeamCount(team), NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true))
          .append(Component.text("人が所属しています。", NamedTextColor.WHITE)));
    }
  }

}
