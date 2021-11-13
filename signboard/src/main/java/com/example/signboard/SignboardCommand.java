package com.example.signboard;

import java.util.Arrays;
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

      if (args.length >= 3 && args[0].equals("write")) {
        String tag = args[1];
        // copyOfRange(対象の配列, 開始インデックス(含む), 終了インデックス(含まない))
        // 終了インデックスはその箇所を「含まない」ので、[0,1,2]の配列なら、3を指定することで最後までになる。
        String[] lines = Arrays.copyOfRange(args, 2, args.length);
        this.main.signboard.showText(player.getWorld(), player.getLocation(), tag, lines);
        return true;
      }

      if (args.length == 2 && args[0].equals("showtag")) {
        System.out.println(args[1]);
        if (args[1].equals("true") || args[1].equals("false")) {
          Boolean isVisible = Boolean.valueOf(args[1]);
          this.main.signboard.showTag(player.getWorld(), isVisible);
          return true;
        }
      }
    }

    return false;
  }
}
