package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import unstudio.chinacraft.api.ItemMethod;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.entity.projectile.EntityThrownFirecracker;
import unstudio.chinacraft.util.ItemLoreHelper;

import java.util.List;

public class ItemFirecracker extends Item {
    public ItemFirecracker() {
        setUnlocalizedName("firecracker");
        setCreativeTab(ChinaCraft.tabTool);
        setMaxStackSize(16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (player.isSneaking()){
                for (int i = 0; i < stack.stackSize; i++) {
                    EntityThrownFirecracker entityThrownFirecracker = new EntityThrownFirecracker(world, player);
                    entityThrownFirecracker.motionX *= world.rand.nextGaussian();
                    entityThrownFirecracker.motionY += 0.3;
                    entityThrownFirecracker.motionZ *= world.rand.nextGaussian();
                    world.spawnEntityInWorld(entityThrownFirecracker);
                }
            } else world.spawnEntityInWorld(new EntityThrownFirecracker(world, player));
        }
        if (!player.capabilities.isCreativeMode) {
            if (player.isSneaking()) {
                stack.stackSize = 0;
            } else --stack.stackSize;
        }

        return stack;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (FeatureConfig.enableShiftShootFirecrackers)
            ItemLoreHelper.shiftLoreWithStat(p_77624_3_, getUnlocalizedName());
    }
}
