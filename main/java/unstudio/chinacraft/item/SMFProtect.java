package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFProtect extends SpiritualMagicFigures{
    public SMFProtect(){
        setUnlocalizedName("spiritual_magic_figures_protect");
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.addPotionEffect(new PotionEffect(12,3500));
        target.addPotionEffect(new PotionEffect(11,2500,3));
        stack.stackSize = stack.stackSize -1;
        return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(12,3500));
        player.addPotionEffect(new PotionEffect(11,2500,3));
        stack.stackSize = stack.stackSize -1;
        return stack;
    }
}
