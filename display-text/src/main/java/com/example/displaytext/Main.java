package com.example.displaytext;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("text").setExecutor(new TextCommand());
  }

}
