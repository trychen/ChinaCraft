package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFPotion extends SpiritualMagicFigures{

    public int[][] Effect;

    public SMFPotion(String name,int[][] a){
        setUnlocalizedName(name);
        this.Effect = a;
    }



    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
            for (int[] a : Effect) {
                target.addPotionEffect(a.length == 2?new PotionEffect(a[0], a[1]):new PotionEffect(a[0], a[1],a[2]));
            }
            --stack.stackSize;
            return false;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        for(int[] a:Effect){
            player.addPotionEffect(a.length == 2?new PotionEffect(a[0], a[1]):new PotionEffect(a[0], a[1],a[2]));
        }
        --stack.stackSize;
        return stack;
    }
}
