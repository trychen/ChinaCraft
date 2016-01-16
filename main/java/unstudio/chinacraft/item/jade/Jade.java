package unstudio.chinacraft.item.jade;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.event.jade.PlayerUseJadeEvent;

public class Jade extends Item {
    private int Level = 1;
    private Random random = new Random();

    public Jade(String s) {
        setUnlocalizedName(s);
        setMaxStackSize(1);
        setMaxDamage(6);
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
        PlayerUseJadeEvent.ItemRightClick e = new PlayerUseJadeEvent.ItemRightClick(entityPlayer,itemStack);
        if (MinecraftForge.EVENT_BUS.post(e)) return false;

        return true;
    }
}
