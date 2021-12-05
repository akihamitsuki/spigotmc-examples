package com.example.teamclassification;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private static Main instance;
  private TeamManager teamManager = new TeamManager();

  @Override
  public void onEnable() {
    Main.instance = this;
    this.getCommand("team").setExecutor(new TeamCommand());
    this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
  }

  public static Main getInstance() {
    return Main.instance;
  }

  public TeamManager getTeamManager() {
    return teamManager;
  }

}
