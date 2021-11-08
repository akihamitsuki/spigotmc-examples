package com.example.entitymanagement;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;

public class EntityManager {

  List<Zombie> zombies = new ArrayList<Zombie>();

  public EntityManager(Main main) {
    main.getServer().getPluginManager().registerEvents(new EntityListener(this), main);
    main.getCommand("entity").setExecutor(new EntityCommand(this));
  }

  public void spawn(Player player) {
    // プレイヤーのワールドを取得(オーバーワールド・ネザー・エンドのいずれにいるのか)
    World world = player.getWorld();
    // プレイヤーの位置を取得
    Location location = player.getLocation();
    // ワールド・位置・エンティティ型を使ってゾンビを呼び出す(この時点でスポーンする)
    Entity entity = world.spawnEntity(location, EntityType.ZOMBIE);
    // 呼び出すのはエンティティ型なので、ゾンビ型に変換する（キャスト）
    Zombie zombie = (Zombie) entity;

    // ここまでを1行でもできる
    // Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

    // 名前を付ける
    zombie.setCustomName("呼び出したゾンビ");
    // 名前を表示する(初期値は非表示)
    zombie.setCustomNameVisible(true);

    // 呼び出したゾンビをリストに登録しておく
    zombies.add(zombie);
  }

  public void speedUp(Zombie zombie) {
    // 対象となる属性(attribute)
    Attribute attr = Attribute.GENERIC_MOVEMENT_SPEED;
    // 現在の移動速度(GENERIC_MOVEMENT_SPEED)を取得する
    double current = zombie.getAttribute(attr).getBaseValue();
    // それを2倍にして設定する
    zombie.getAttribute(attr).setBaseValue(current * 2);
  }

  public void speedUpAll() {
    for (Zombie zombie : zombies) {
      this.speedUp(zombie);
    }
  }

  public void launch(Zombie zombie) {
    // speed(速さ/スカラー)に向きの要素を加えたのがvelocity(速度/ベクトル)
    Vector velocity = zombie.getVelocity();
    // 真上に速度を加えて設定する
    zombie.setVelocity(velocity.add(new Vector(0, 5, 0)));
    // 演出を加える
    zombie.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, zombie.getLocation(), 30);
    zombie.getWorld().playSound(zombie.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 3, 0);
  }

  public void launchAll() {
    for (Zombie zombie : zombies) {
      this.launch(zombie);
    }
  }

  public void remove(Zombie zombie) {
    zombies.remove(zombies.indexOf(zombie));
  }

  public void removeAll() {
    this.zombies = new ArrayList<Zombie>();
  }
}
