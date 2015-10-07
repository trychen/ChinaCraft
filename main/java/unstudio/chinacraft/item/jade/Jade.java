package unstudio.chinacraft.item.jade;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.ChinaCraft;

public class Jade extends Item{
	private int Level = 1;
	private Random random = new Random();
	public Jade (String s) {
		setUnlocalizedName(s);
		setMaxStackSize(16);
		setCreativeTab(ChinaCraft.tabCore);
	}
	public Jade (String s,int level) {
		setUnlocalizedName(s);
		setMaxStackSize(16);
		setCreativeTab(ChinaCraft.tabCore);
	}

	public int getEnchantability()
	{
		return 3;
	}
}
