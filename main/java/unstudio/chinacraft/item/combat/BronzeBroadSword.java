package unstudio.chinacraft.item.combat;


import net.minecraft.item.ItemSword;
import unstudio.chinacraft.common.ChinaCraft;

public class BronzeBroadSword extends ItemSword{
	public BronzeBroadSword(ToolMaterial toolMaterial,String name) {
		super(toolMaterial);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
