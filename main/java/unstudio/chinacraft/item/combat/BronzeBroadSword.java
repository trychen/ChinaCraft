package unstudio.chinacraft.item.combat;


import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class BronzeBroadSword extends ItemSword{
	public BronzeBroadSword(String name) {
		super(ChinaCraft._BRONZE);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
