package com.example.teamclassification;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Team {

  // チームの種類(名称, GUIでのアイテム)
  RED(ChatColor.RED + "赤チーム", Material.RED_WOOL),
  BLUE(ChatColor.BLUE + "青チーム", Material.BLUE_WOOL),
  GREEN(ChatColor.GREEN + "緑チーム", Material.GREEN_WOOL);

  // インベントリの名前=GUIの名前。ここでどのGUIが選ばれたのかを判定する
  private static String title = ChatColor.BOLD + "チームを選んでください";
  // チーム名
  private String name;
  // GUIでのアイテム
  private Material material;

  private Team(String name, Material material) {
    this.name = name;
    this.material = material;
  }

  // getter

  public String getName() {
    return this.name;
  }

  public Material getMaterial() {
    return material;
  }

  public static String getTitle() {
    return title;
  }

}
