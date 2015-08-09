package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFPoison extends SpiritualMagicFigures{
    public SMFPoison(){
        setUnlocalizedName("spiritual_magic_figures_night_poison");
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.addPotionEffect(new PotionEffect(19,500,4));
        stack.stackSize = stack.stackSize -1;
        return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(19, 250,2));
        stack.stackSize = stack.stackSize -1;
        return stack;
    }
}