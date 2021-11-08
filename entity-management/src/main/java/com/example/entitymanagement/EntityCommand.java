package com.example.entitymanagement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EntityCommand implements CommandExecutor {

  private EntityManager entityManager;

  public EntityCommand(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (args.length == 1) {
      if (args[0].equals("spawn")) {
        if (sender instanceof Player) {
          this.entityManager.spawn((Player) sender);
          return true;
        }
      }

      if (args[0].equals("speedup")) {
        this.entityManager.speedUpAll();
        return true;
      }

      if (args[0].equals("launch")) {
        this.entityManager.launchAll();
        return true;
      }

      if (args[0].equals("remove")) {
        this.entityManager.removeAll();
        return true;
      }
    }

    return false;
  }
}
