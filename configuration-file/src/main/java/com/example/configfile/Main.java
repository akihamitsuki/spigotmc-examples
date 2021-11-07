package com.example.configfile;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    // 設定ファイルの操作をまとめたクラス
    new Config(this);
    this.getCommand("config").setExecutor(new ConfigCommand());
  }

}
