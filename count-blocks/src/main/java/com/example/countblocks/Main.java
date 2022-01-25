package com.example.countblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("count").setExecutor(new CountCommand());
  }

}
