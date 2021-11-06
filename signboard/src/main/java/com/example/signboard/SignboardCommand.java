package com.example.signboard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SignboardCommand implements CommandExecutor {

  Main main;

  public SignboardCommand(Main main) {
    this.main = main;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      Player player = (Player) sender;
      this.main.signboard.showText(player.getWorld(), player.getLocation(), args);
      return true;
    }

    return false;
  }
}
