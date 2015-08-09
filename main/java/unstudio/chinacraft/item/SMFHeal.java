package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFHeal extends SpiritualMagicFigures{
    public SMFHeal(){
        setUnlocalizedName("spiritual_magic_figures_night_heal");
    };
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.addPotionEffect(new PotionEffect(6,2));
        target.addPotionEffect(new PotionEffect(10,500));
        stack.stackSize = stack.stackSize -1;
        return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(6, 1));
        player.addPotionEffect(new PotionEffect(10,500));
        stack.stackSize = stack.stackSize -1;
        return stack;
    }
}
