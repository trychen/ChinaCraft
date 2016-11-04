package unstudio.chinacraft.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.projectile.EntityProjectile;
import unstudio.chinacraft.entity.projectile.EntitySuperArrow;

/**
 * Use for nothing. Created by trychen on 15/12/6.
 */
public class ItemSuperBow extends ItemBow {
    public ItemSuperBow() {
        this.setMaxDamage(437);
        this.setCreativeTab(ChinaCraft.tabTool);
        this.setUnlocalizedName("super_bow");
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int parInt) {
        ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, parInt);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }

        if (world.isRemote) {
            player.swingItem();
            return;
        }

        EntityProjectile projectile = new EntityProjectile(world, player, new ItemStack(Items.arrow, 1, itemStack.getItemDamage()));
        projectile.canBePickedUp = player.capabilities.isCreativeMode;
        projectile.setRotating(false);
        projectile.damage = 5;
        projectile.setArrow(true);
        projectile.hitSound = "arrow.hit";
        projectile.setParticleEffect(EntityProjectile.EnumParticleType.Crit);
        projectile.setIs3D(true);
        projectile.setStickInWall(true);
        projectile.setHasGravity(false);
        projectile.setSpeed(24);
        if (!player.capabilities.isCreativeMode) {
            player.inventory.consumeInventoryItem(this);
        }
        projectile.shoot(2.0F);
        world.spawnEntityInWorld(projectile);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        ArrowNockEvent event = new ArrowNockEvent(p_77659_3_, p_77659_1_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return event.result;
        }

        if (p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(Items.arrow)) {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }
}
