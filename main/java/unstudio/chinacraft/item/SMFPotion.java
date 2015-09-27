package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SMFPotion extends SpiritualMagicFigures{

    public int[][] Effect;
    public int b = 0;

    public SMFPotion(String name,int[][] a){
        setUnlocalizedName(name);
        this.Effect = a;
    }
    public SMFPotion(String name,int[][] a,int b){
        setUnlocalizedName(name);
        this.Effect = a;
        this.b = b;
    }



    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (Effect != null) {
            return false;
        }
        if (((EntityPlayer)attacker).experienceLevel < b){
            return false;
        } else {
            ((EntityPlayer)attacker).addExperience(-5);
        }
        for (int[] a : Effect) {
            target.addPotionEffect(a.length == 2 ? new PotionEffect(a[0], a[1]) : new PotionEffect(a[0], a[1], a[2]));
        }
        --stack.stackSize;
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (Effect != null) {
            return stack;
        }
        if (player.experienceLevel < b){
            return stack;
        } else {
            player.addExperience(-5);
        }
        for (int[] a : Effect) {
            player.addPotionEffect(a.length == 2 ? new PotionEffect(a[0], a[1]) : new PotionEffect(a[0], a[1], a[2]));
        }

        --stack.stackSize;
        return stack;
    }
}
