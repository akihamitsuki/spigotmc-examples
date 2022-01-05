package com.example.guiinventory;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {

  public String equipmentUIName = "装備するアイテムを選んでください";

  public void displayEquipmentUI(Player player) {
    // インベントリの基本情報
    Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.BOLD + equipmentUIName);
    // 配置するアイテムの情報を作成する(これをクリックしたときを条件にする)
    // エリトラ
    ItemStack elytra = new ItemStack(Material.ELYTRA);
    ItemMeta elytraMeta = elytra.getItemMeta();
    elytraMeta.setDisplayName(ChatColor.DARK_PURPLE + "エリトラを装備する");
    elytra.setItemMeta(elytraMeta);
    // ダイアモンド
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.setDisplayName(ChatColor.GREEN + "ダイヤモンドの防具を装備する");
    List<String> diamondLore = new ArrayList<>();
    diamondLore.add(ChatColor.GRAY + "装備されていない箇所に");
    diamondLore.add(ChatColor.GRAY + "ダイヤモンドの防具を装備します");
    diamondMeta.setLore(diamondLore);
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
