package me.sedano.bombermob.tasks;

import me.sedano.bombermob.BomberMob;
import org.bukkit.Bukkit;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class BomberTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getWorlds()
                .stream()
                .flatMap(world -> world.getLivingEntities().stream())
                .filter(livingEntity -> livingEntity.getPersistentDataContainer().has(BomberMob.Keys.BOMBER_MOB_KEY, PersistentDataType.STRING))
                .forEach(livingEntity -> livingEntity.getWorld().createExplosion(livingEntity, livingEntity.getLocation(), 3.0F, false));
    }
}