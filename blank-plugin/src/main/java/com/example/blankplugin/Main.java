package com.example.blankplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("プラグインを有効にしました。");
  }
}
