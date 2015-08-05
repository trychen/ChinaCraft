package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SMFFire extends SpiritualMagicFigures{
    public SMFFire(){
        setUnlocalizedName("spiritual_magic_figures_fire");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.setFire(target.worldObj.rand.nextInt(8) + 2);
        stack.stackSize = stack.stackSize -1;
        return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        stack.stackSize = stack.stackSize -1;
        player.setFire(3);
        return stack;
    }
}
