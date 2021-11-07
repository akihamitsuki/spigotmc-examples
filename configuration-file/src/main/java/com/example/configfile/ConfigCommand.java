package com.example.configfile;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length == 1) {
        if (args[0].equals("spawn")) {
          // スポーン位置を設定から取得
          Location spawnLocation = Config.getZombieSpawnLocation();
          // ゾンビを呼び出す
          player.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
          player.sendMessage("ゾンビを設定箇所に呼び出しました。");
          return true;
        }

        if (args[0].equals("save")) {
          // 設定をファイルに保存する
          Config.save();
          player.sendMessage("設定を保存しました。");
          return true;
        }

        if (args[0].equals("set")) {
          // 設定を変更する
          Config.setZombieSpawnLocation(player);
          Location location = player.getLocation();
          player.sendMessage("ゾンビの出現位置を "
              + location.getWorld() + "("
              + location.getX() + ", "
              + location.getY() + ", "
              + location.getZ() + ") に設定しました。");
          return true;
        }

        if (args[0].equals("get")) {
          Location location = Config.getZombieSpawnLocation();
          sender.sendMessage("ゾンビの出現位置は "
              + location.getWorld() + "("
              + location.getX() + ", "
              + location.getY() + ", "
              + location.getZ() + ") です。");
          return true;
        }
      }
    }

    return false;
  }

}
