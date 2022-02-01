package com.example.guiinventory;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Menu {

  public Component equipmentUIName = Component.text("装備するアイテムを選んでください").decoration(TextDecoration.BOLD, true);

  public void displayEquipmentUI(Player player) {
    // インベントリの基本情報
    Inventory inventory = Bukkit.createInventory(null, 9, equipmentUIName);
    // 配置するアイテムの情報を作成する(これをクリックしたときを条件にする)
    // エリトラ
    ItemStack elytra = new ItemStack(Material.ELYTRA);
    ItemMeta elytraMeta = elytra.getItemMeta();
    elytraMeta.displayName(Component.text("エリトラを装備する").color(NamedTextColor.DARK_PURPLE));
    elytra.setItemMeta(elytraMeta);
    // ダイアモンド
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.displayName(Component.text("ダイヤモンドの防具を装備する").color(NamedTextColor.GREEN));
    List<Component> diamondLore = new ArrayList<>();
    diamondLore.add(Component.text("装備されていない箇所に").color(NamedTextColor.GRAY));
    diamondLore.add(Component.text("ダイヤモンドの防具を装備します").color(NamedTextColor.GRAY));
    diamondMeta.lore(diamondLore);
    diamond.setItemMeta(diamondMeta);
    // アイテムをインベントリに配置する
    inventory.setItem(0, elytra);
    inventory.setItem(1, diamond);
    // インベントリを開く
    player.openInventory(inventory);
  }

  /**
   * エリトラを装備する
   *
   * @param player
   */
  public void equipElytra(Player player) {
    // エリトラを装備させる(元の防具は消える)
    player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
    player.sendMessage("エリトラを装備しました。");
  }

  /**
   * 装備のない箇所にダイヤモンドの防具を装備する
   *
   * @param player 対象プレイヤー
   */
  public void equipDiamondArmor(Player player) {
    PlayerInventory inventory = player.getInventory();

    if (inventory.getHelmet() == null) {
      inventory.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
    }
    if (inventory.getChestplate() == null) {
      inventory.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
    }
    if (inventory.getLeggings() == null) {
      inventory.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
    }
    if (inventory.getBoots() == null) {
      inventory.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
    }

    player.sendMessage("ダイヤモンドの防具を装備しました。");
  }

}
