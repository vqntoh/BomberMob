package me.sedano.bombermob.tasks;

import me.sedano.bombermob.BomberMob;
import me.sedano.bombermob.entity.Bomber;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class BomberTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getWorlds().forEach(world -> {
            for (LivingEntity entity : world.getLivingEntities()) {
                if (!entity.getPersistentDataContainer().has(new NamespacedKey(BomberMob.getInstance(), Bomber.BOMBER_KEY), PersistentDataType.STRING))
                    continue;
                entity.getWorld().createExplosion(entity, entity.getLocation(), 3.0F, false);
            }
        });
    }
}
