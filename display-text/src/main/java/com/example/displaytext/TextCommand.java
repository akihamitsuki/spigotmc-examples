package com.example.displaytext;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TextCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length == 1) {
        // 名前の付いた防具立てを表示
        Text.spawnArmorStand(player.getWorld(), player.getLocation(), args[0]);

        return true;
      }
    }

    return false;
  }
}
