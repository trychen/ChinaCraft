package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeBroadSword extends ItemSword{
	public BronzeBroadSword() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_broadsword");
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
