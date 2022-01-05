package com.example.gametemplate;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * ロビー(lobby)に関するクラス
 */
public class Lobby {

  public void teleportLobby(Player player) {
    // プレイヤーを初期化する
    initializePlayer(player);
    // プレイヤーをロビーに移動させる
    player.teleport(Config.getLobbySpawn());
  }

  /**
   * ロビーにおける初期状態を設定する
   *
   * @param player 対象プレイヤー
   */
  private void initializePlayer(Player player) {
    player.getInventory().clear();
    player.setGameMode(GameMode.ADVENTURE);
    // エフェクトをすべて消す
    for (PotionEffect effect : player.getActivePotionEffects()) {
      player.removePotionEffect(effect.getType());
    }
    // 個別にエフェクトをかけなおす
    player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 255, false, false));
    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 255, false, false));
    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 255, false, false));
  }

}
