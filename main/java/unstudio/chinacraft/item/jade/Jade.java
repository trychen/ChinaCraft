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
		setlevel();
	}
	public Jade (String s,int level) {
		setUnlocalizedName(s);
		setMaxStackSize(16);
		setCreativeTab(ChinaCraft.tabCore);
		setlevel(level);
	}
	public void setlevel() {
		this.Level = random.nextInt(2)+1;
	}
	public void setlevel(int level) {
		this.Level = level;
	}
	public int getlevel() {
		return this.Level;
	}
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(StatCollector.translateToLocal("item.jade.lore") + this.getlevel());
	}

	public int getEnchantability()
	{
		return 3;
	}
}
