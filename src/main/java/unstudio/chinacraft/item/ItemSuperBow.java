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
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int parInt) {
        int j = this.getMaxItemUseDuration(itemStack) - parInt;

        ArrowLooseEvent event = new ArrowLooseEvent(entityPlayer, itemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }
        j = event.charge;

        boolean flag = entityPlayer.capabilities.isCreativeMode
                || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

        if (flag || entityPlayer.inventory.hasItem(Items.arrow)) {
            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntitySuperArrow entityarrow = new EntitySuperArrow(world, entityPlayer, f * 2.0F);

            entityarrow.canBePickedUp = 0;

            if (f == 1.0F) {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);

            if (k > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);

            if (l > 0) {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0) {
                entityarrow.setFire(100);
            }

            itemStack.damageItem(1, entityPlayer);
            world.playSoundAtEntity(entityPlayer, "random.bow", 1.0F,
                    1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) {
                entityarrow.canBePickedUp = 2;
            } else {
                entityPlayer.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!world.isRemote) {
                world.spawnEntityInWorld(entityarrow);
            }
        }
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
