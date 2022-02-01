package com.example.teamclassification;

import org.bukkit.Material;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Team {

  // チームの種類(名称, GUIでのアイテム)
  RED("赤チーム", NamedTextColor.RED, Material.RED_WOOL),
  BLUE("青チーム", NamedTextColor.BLUE, Material.BLUE_WOOL),
  GREEN("緑チーム", NamedTextColor.GREEN, Material.GREEN_WOOL);

  // インベントリの名前=GUIの名前。ここでどのGUIが選ばれたのかを判定する
  private static String title = "チームを選んでください";
  // チーム名
  private String name;
  // チーム文字色
  private NamedTextColor textColor;
  // GUIでのアイテム
  private Material material;

  private Team(String name, NamedTextColor textColor, Material material) {
    this.name = name;
    this.textColor = textColor;
    this.material = material;
  }

  // getter

  public String getName() {
    return this.name;
  }

  public NamedTextColor getTextColor() {
    return this.textColor;
  }

  public Material getMaterial() {
    return material;
  }

  public static String getTitle() {
    return title;
  }

}
