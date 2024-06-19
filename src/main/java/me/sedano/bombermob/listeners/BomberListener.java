package me.sedano.bombermob.listeners;

import me.sedano.bombermob.BomberMob;
import me.sedano.bombermob.entity.Bomber;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BomberListener implements Listener {
    @EventHandler
    public void onPlayerSpawnBomberEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (player.getInventory().getItemInHand() == null) return;
        ItemStack item = player.getInventory().getItemInHand();

        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();

        if (!meta.getPersistentDataContainer().has(BomberMob.Keys.BOMBER_EGG_KEY, PersistentDataType.STRING))
            return;

        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();

        new Bomber(block.getLocation().add(0.5, 1.1, 0.5));
    }

    @EventHandler
    public void onPlayerThrowBomberEvent(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof Egg)) return;
        ItemStack item = ((Egg) event.getEntity()).getItem();

        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();

        if (!meta.getPersistentDataContainer().has(BomberMob.Keys.BOMBER_EGG_KEY, PersistentDataType.STRING))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onExplosionHitBomber(EntityDamageEvent event) {
        if (event.getEntity().getPersistentDataContainer().has(BomberMob.Keys.BOMBER_MOB_KEY, PersistentDataType.STRING))
            if (event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
                return;
        event.setCancelled(true);
    }

    public static class BomberEggCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack item = new ItemStack(Material.EGG);
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(BomberMob.Keys.BOMBER_EGG_KEY, PersistentDataType.STRING, Bomber.BOMBER_EGG_KEY);
                meta.setDisplayName(BomberMob.Config.bomberEggName());
                meta.setLore(BomberMob.Config.bomberEggLore());
                meta.setCustomModelData(BomberMob.Config.bomberEggCustomModelData());
                item.setItemMeta(meta);

                player.getInventory().addItem(item);
                return true;
            }
            return false;
        }
    }
}
