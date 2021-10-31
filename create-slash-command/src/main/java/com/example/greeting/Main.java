package com.example.greeting;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    // greetコマンドを使った時に実行するクラスを設定する
    // "greet"はplugin.ymlで設定した値
    this.getCommand("greet").setExecutor(new CommandGreet());
  }
}
