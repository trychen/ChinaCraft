package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.api.ItemMethod;
import unstudio.chinacraft.entity.EntityThrownFirecracker;

public class ItemFirecracker extends Item{
    public ItemFirecracker(){
        setUnlocalizedName("firecracker");
        setCreativeTab(ChinaCraft.tabCore);
        setMaxStackSize(16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        world.spawnEntityInWorld(new EntityThrownFirecracker(world,player));
        return ItemMethod.cutItemStack(stack,player);
    }
}
