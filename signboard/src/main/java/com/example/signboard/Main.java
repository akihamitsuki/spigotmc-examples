package com.example.signboard;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  Signboard signboard;

  @Override
  public void onEnable() {
    this.signboard = new Signboard();
    this.getCommand("signboard").setExecutor(new SignboardCommand(this));
  }

}
