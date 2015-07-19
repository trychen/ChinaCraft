package unstudio.chinacraft.combat;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeHoe extends ItemHoe{
	public BronzeHoe() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_hoe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
