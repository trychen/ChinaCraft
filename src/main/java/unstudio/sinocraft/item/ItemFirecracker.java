package unstudio.sinocraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import unstudio.sinocraft.api.ItemMethod;
import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.entity.projectile.EntityThrownFirecracker;

public class ItemFirecracker extends Item {
    public ItemFirecracker() {
        setUnlocalizedName("firecracker");
        setCreativeTab(SinoCraft.tabTool);
        setMaxStackSize(16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityThrownFirecracker(world, player));
        }
        return ItemMethod.cutItemStack(stack, player);
    }
}
