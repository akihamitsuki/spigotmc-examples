package com.example.guiinventory;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  public Menu menu;

  @Override
  public void onEnable() {
    this.menu = new Menu();
    this.getCommand("menu").setExecutor(new MenuCommand(this));
    Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
  }

}
