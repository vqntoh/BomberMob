package me.sedano.bombermob;

import me.sedano.bombermob.listeners.BomberListener;
import me.sedano.bombermob.tasks.BomberTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class BomberMob extends JavaPlugin {

    private static BomberMob instance;

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new BomberListener(), this);
        getCommand("bomberegg").setExecutor(new BomberListener.BomberEggCommand());

        new BomberTask().runTaskTimer(this, 0L, 20L * Config.bomberExplosionTime());
    }

    public static BomberMob getInstance() {
        return instance;
    }

    public static class Config {
        public static String bomberEggName() {
            return instance.getConfig().getString("bomber-egg-name");
        }

        public static List<String> bomberEggLore() {
            return instance.getConfig().getStringList("bomber-egg-lore");
        }

        public static int bomberEggCustomModelData() {
            return instance.getConfig().getInt("bomber-egg-custom-model-data");
        }

        public static int bomberExplosionTime() {
            return instance.getConfig().getInt("bomber-explosion-time");
        }

        public static int bomberHealth() {
            return instance.getConfig().getInt("bomber-health");
        }
    }
}
