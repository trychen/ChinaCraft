package unstudio.chinacraft.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.util.ItemLoreHelper;

import java.util.List;

/**
 * Created by trychen on 16/7/31.
 */
public class ItemBuddhistCane extends ItemCCStaff {
    public ItemBuddhistCane() {
        setUnlocalizedName("buddhist_cane");
        setMaxDamage(730);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.ticksExisted % 6 != 0) return;
        if (!player.worldObj.isRemote) {
            List<EntityMob> mobs = EntityMethod.findNear(player, EntityMob.class, 8, 8);
            List<EntityPlayer> players = EntityMethod.findNear(player, EntityPlayer.class, 5, 5);
            boolean hasUnMaxhealthPlayer = false;
            float heal = 0.15f;
            int healCount = 6;
            for (EntityPlayer entityPlayer : players) {
                if (entityPlayer.getMaxHealth() != entityPlayer.getHealth()) {
                    hasUnMaxhealthPlayer = true;
                }
                if (healCount < 1) break;
                entityPlayer.setHealth(entityPlayer.getHealth() + heal);
                healCount--;
            }
            if (mobs.size() == 0 && !hasUnMaxhealthPlayer) {
                hasAnimal(EntityMethod.findNear(player, EntityAnimal.class, 5, 5));
            }
        }
        double x = player.posX + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 6 : -6);
        double y = player.posY + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 1 : -1);
        double z = player.posZ + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 6 : -6);
        player.worldObj.spawnParticle("happyVillager", x, y, z, 0, 0, 0);
    }

    public int hasAnimal(List<EntityAnimal> animals) {
        for (EntityAnimal animal : animals) {
            if (animal.getHealth() != animal.getMaxHealth()) {
                return 0;
            }
        }
        int count = animals.size() > 6 ? 6 : animals.size();
        int add = count > 3 ? 3 : count;
        return add;
    }

    public boolean isDamageable() {
        return true;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemLoreHelper.shiftLoreWithStat(p_77624_3_,getUnlocalizedName());
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        return super.getAttributeModifiers(stack);

    }

    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase p_150894_7_) {
        if (itemStack.getItemDamage() > 0&&(block instanceof BlockLeaves||block instanceof BlockMushroom||block instanceof BlockBush)&& world.rand.nextBoolean()){
             itemStack.setItemDamage(itemStack.getItemDamage() - 1);
            world.spawnParticle("happyVillager", x, y, z, 0, 0, 0);
        }
        return false;
    }
}
