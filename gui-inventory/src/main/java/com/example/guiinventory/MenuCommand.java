package com.example.guiinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

  private Menu menu;

  public MenuCommand(Main main) {
    this.menu = main.menu;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      this.menu.displayEquipmentUI((Player) sender);
      return true;
    }

    return false;
  }

}
