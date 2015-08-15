package unstudio.chinacraft.item.jade;

import java.util.Random;

import net.minecraft.item.Item;
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
}
