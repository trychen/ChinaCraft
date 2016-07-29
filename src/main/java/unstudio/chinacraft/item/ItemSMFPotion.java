package unstudio.chinacraft.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;

import java.util.Arrays;
import unstudio.chinacraft.api.ItemMethod;
import unstudio.chinacraft.common.ChinaCraft;

public class ItemSMFPotion extends Item {

    public int[][] effect;
    public int b = 0;

    public ItemSMFPotion(String name, int[][] a) {
        this(name, a, 0);
    }

    public ItemSMFPotion(String name, int[][] a, int b) {
        setUnlocalizedName(name);
        setCreativeTab(ChinaCraft.tabCore);
        setMaxStackSize(8);
        this.effect = a;
        this.b = b;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target.worldObj.isRemote) {
            if (effect == null) {
                return false;
            }
            if (((EntityPlayer) attacker).experienceLevel < b) {
                return false;
            } else {
                ((EntityPlayer) attacker).addExperience(-5);
            }
            for (int[] a : effect) {
                PotionEffect potionEffect = a.length == 2 ? new PotionEffect(a[0], a[1]) : new PotionEffect(a[0], a[1], a[2]);
                potionEffect.setCurativeItems(Arrays.asList(new ItemStack(ChinaCraft.blackDogBlood)));
                attacker.addPotionEffect(potionEffect);
            }
            --stack.stackSize;
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            if (effect == null) {
                return stack;
            }
            if (player.experienceLevel < b) {
                return stack;
            } else {
                player.addExperience(-5);
            }
            for (int[] a : effect) {
                PotionEffect potionEffect = a.length == 2 ? new PotionEffect(a[0], a[1]) : new PotionEffect(a[0], a[1], a[2]);
                System.out.println(potionEffect);
                potionEffect.setCurativeItems(new ArrayList<ItemStack>());
                player.addPotionEffect(potionEffect);
            }
        }
        return ItemMethod.cutItemStack(stack, player);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (effect == null)
            return;
        for (int[] i : effect) {
            PotionEffect p = new PotionEffect(i[0], i[1]);
            if (i.length == 2) {
                p_77624_3_.add(I18n.format(p.getEffectName()) + " " + Potion.getDurationString(p));
            } else {
                p_77624_3_.add(I18n.format(p.getEffectName()) + i[2] + " "
                        + Potion.getDurationString(p));
            }
        }
    }
}
