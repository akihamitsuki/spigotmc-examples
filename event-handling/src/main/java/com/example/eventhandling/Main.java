package com.example.eventhandling;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    // イベント検知のクラスを登録する
    // 第一引数が登録するクラスのインスタンス
    // 第二引数がJavaPlugin(Main)のインスタンス、ここではthis
    this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
  }
}
