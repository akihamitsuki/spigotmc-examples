package com.example.countblocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CountCommand implements CommandExecutor {

  Integer minRange = 0;
  Integer maxRange = 100;

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      if (sender instanceof Player) {
        Player player = (Player) sender;
        // 範囲の初期値
        Integer range = 0;
        // 例外処理
        try {
          // 第一引数が整数でなければここでエラーが起きる
          range = Integer.parseInt(args[0]);
          // 範囲を確認
          if (range < minRange || range > maxRange) {
            sender.sendMessage(minRange + "~" + maxRange + "の整数で入力してください。");
            return false;
          }
        } catch (NumberFormatException e) {
          // 整数以外が入力された場合
          sender.sendMessage("範囲は整数を入力してください");
          return false;
        }
        return this.countBlocks(player, range);
      }
    }
    return false;
  }

  private boolean countBlocks(Player player, Integer range) {
    Map<Material, Integer> blocks = getBlockCountMap(player, range);

    // 並び変えしない場合
    // for(Entry<Material, Integer> entry : blocks.entrySet()) {
    //   player.sendMessage(entry.getKey().toString() + ": " + entry.getValue().toString());
    // }

    // 並び替えする場合
    List<Entry<Material, Integer>> entries = new ArrayList<Entry<Material, Integer>>(blocks.entrySet());
    Collections.sort(entries, new Comparator<Entry<Material, Integer>>() {
      public int compare(Entry<Material, Integer> obj1, Entry<Material, Integer> obj2) {
        return obj2.getValue().compareTo(obj1.getValue());
      }
    });
    // 表示
    player.sendMessage("---");
    for (Entry<Material, Integer> entry : entries) {
      player.sendMessage(entry.getKey().toString() + ": " + entry.getValue().toString());
    }

    return true;
  }

  /**
   * ブロックの種類別の数を取得する
   *
   * @param player
   * @param range
   * @return
   */
  private Map<Material, Integer> getBlockCountMap(Player player, Integer range) {
    Map<Material, Integer> blocks = new HashMap<>();
    // プレイヤーの座標を基準座標として取得する
    Location playerLocation = player.getLocation();

    for (int x = -range; x <= range; x++) {
      for (int y = -range; y <= range; y++) {
        for (int z = -range; z <= range; z++) {
          // 基準座標を複製する(ここでクローンを使わないと基準座標自体が変更されてしまう)
          Location targetLocation = playerLocation.clone();
          // 対象座標からブロックの種類を取得する
          Material material = targetLocation.add(x, y, z).getBlock().getType();
          // ここまでの合計数を取得
          Integer total = blocks.get(material);
          // 合計がnullなら0とする（）
          if (total == null) {
            total = 0;
          }
          // 合計を加算して上書き
          blocks.put(material, total + 1);
        }
      }
    }

    return blocks;
  }
}


