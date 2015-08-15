package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeBroadSword extends ItemSword{
	public BronzeBroadSword() {
		super(ChinaCraft._BRONZE);
		setUnlocalizedName("bronze_broadsword");
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
