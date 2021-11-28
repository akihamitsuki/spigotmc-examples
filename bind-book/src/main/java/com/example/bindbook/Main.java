package com.example.bindbook;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("book").setExecutor(new BookCommand());
  }

}
