package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeShovel extends ItemSpade{

	public BronzeShovel() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_pickaxe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
