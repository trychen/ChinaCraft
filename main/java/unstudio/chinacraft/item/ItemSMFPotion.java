package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemSMFPotion extends ItemSpiritualMagicFigures{

    public int[][] Effect;
    public int b = 0;

    public ItemSMFPotion(String name, int[][] a){
        setUnlocalizedName(name);
        this.Effect = a;
    }
    public ItemSMFPotion(String name, int[][] a, int b){
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
    
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (Effect == null) return;
        for(int[] i:Effect){
            PotionEffect p = new PotionEffect(i[0], i[1]);
            if (i.length == 2) {
                p_77624_3_.add(StatCollector.translateToLocal(p.getEffectName()) + " " + Potion.getDurationString(p));
            } else {
                p_77624_3_.add(StatCollector.translateToLocal(new PotionEffect(i[0], i[1]).getEffectName()) + i[2] + " " + Potion.getDurationString(p));
            }
        }
    }
}
