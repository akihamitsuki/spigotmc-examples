package com.example.entitymanagement;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    new EntityManager(this);
  }

}
