package me.sedano.bombermob.tasks;

import me.sedano.bombermob.BomberMob;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class BomberTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getWorlds().forEach(world -> {
            for (LivingEntity entity : world.getLivingEntities()) {
                if (!entity.getPersistentDataContainer().has(BomberMob.Keys.BOMBER_MOB_KEY, PersistentDataType.STRING))
                    continue;
                entity.getWorld().createExplosion(entity, entity.getLocation(), 3.0F, false);
            }
        });
    }
}
