package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFPower extends SpiritualMagicFigures{
    public SMFPower(){
        setUnlocalizedName("spiritual_magic_figures_power");
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.addPotionEffect(new PotionEffect(5,target.worldObj.rand.nextInt(2000)+5000));
        stack.stackSize = stack.stackSize -1;
        return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(5, 7000));
        stack.stackSize = stack.stackSize -1;
        return stack;
    }
}
