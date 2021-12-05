package com.example.teamclassification;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length == 0) {
        // GUIを開く
        new TeamGui(player);
      }
    }

    return false;
  }

}
