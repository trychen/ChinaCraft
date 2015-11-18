package unstudio.chinacraft.item.jade;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.ChinaCraft;

public class Jade extends Item {
    private int Level = 1;
    private Random random = new Random();

    public Jade(String s) {
        setUnlocalizedName(s);
        setMaxStackSize(8);
        setMaxDamage(4);
        setCreativeTab(ChinaCraft.tabCore);
    }

    public Jade(String s, int level) {
        setUnlocalizedName(s);
        setMaxStackSize(16);
        setCreativeTab(ChinaCraft.tabCore);
    }

    public int getEnchantability() {
        return 3;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (itemStack.getItem().equals(ChinaCraft.jadeGreen2Item)&&ChinaCraft.jadehasHealTicker == 0) {
            if (itemStack.getItemDamage() == 0) {
                entityPlayer.setHealth(entityPlayer.getHealth() + 6.0f);
                itemStack.setItemDamage(itemStack.getMaxDamage());
                ChinaCraft.jadehasHealTicker = 1800;
                return true;
            }
        }
        return false;
    }
}
