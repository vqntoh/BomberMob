package me.sedano.bombermob.entity;

import me.sedano.bombermob.BomberMob;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class Bomber extends EntityZombie {
    public static final String BOMBER_MOB_KEY = "BomberMob";
    public static final String BOMBER_EGG_KEY = "BomberEgg";
    public static final String CUSTOM_NAME = ChatColor.RED + "T" + ChatColor.DARK_GRAY + "N" + ChatColor.RED + "T" + ChatColor.GRAY + " Zombie";

    public Bomber(Location location) {
        super(((CraftWorld) location.getWorld()).getHandle());

        setHealth((float) BomberMob.Config.bomberHealth());
        setBaby(true);
        setCustomName(IChatBaseComponent.a(CUSTOM_NAME));
        setCustomNameVisible(true);
        setShouldBurnInDay(false);
        setPositionRaw(location.getX(), location.getY(), location.getZ());

        getBukkitEntity().getPersistentDataContainer().set(BomberMob.Keys.BOMBER_MOB_KEY, PersistentDataType.STRING, BOMBER_MOB_KEY);
        getBukkitEntity().getHandle().setSlot(EnumItemSlot.f, net.minecraft.world.item.ItemStack.fromBukkitCopy(new ItemStack(Material.TNT)));
        ((CraftWorld) location.getWorld()).getHandle().addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
