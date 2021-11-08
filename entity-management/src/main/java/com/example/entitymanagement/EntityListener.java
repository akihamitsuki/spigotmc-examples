package com.example.entitymanagement;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntityListener implements Listener {

  private EntityManager entityManager;

  public EntityListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * ゾンビが死亡したときにリストから削除する
   *
   * @param event
   */
  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {
    if (event.getEntityType().equals(EntityType.ZOMBIE)) {
      this.entityManager.zombies.remove((Zombie) event.getEntity());
    }
  }

  /**
   * 呼び出したゾンビを攻撃したとき上に吹き飛ばす
   *
   * @param event
   */
  @EventHandler
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    // 攻撃されたエンティティがゾンビでなければ
    if (!event.getEntityType().equals(EntityType.ZOMBIE)) {
      // 処理を中断(早めに返すことで、入れ子構造を減らすことができる)
      return;
    }

    Zombie zombie = (Zombie) event.getEntity();
    // リストに対象ゾンビが含まれていれば(自分で呼び出したゾンビなら)
    if (this.entityManager.zombies.contains(zombie)) {
      // 攻撃を無効化
      event.setCancelled(true);
      // 上空に吹き飛ばす
      this.entityManager.launch(zombie);
    }
  }

  /**
   * ゾンビが出現したときに速さを上げる
   *
   * @param event
   */
  @EventHandler
  public void onEntitySpawn(EntitySpawnEvent event) {
    if (event.getEntityType().equals(EntityType.ZOMBIE)) {
      // 移動速度を上げる
      this.entityManager.speedUp((Zombie) event.getEntity());
    }
  }

  /**
   * ゾンビが太陽の光で燃えないようにする
   *
   * @param event
   */
  @EventHandler
  public void onEntityCombustEvent(EntityCombustEvent event) {
    if (event.getEntityType().equals(EntityType.ZOMBIE)) {
      event.setCancelled(true);
    }
  }

}
