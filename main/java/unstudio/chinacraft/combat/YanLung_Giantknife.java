package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class YanLung_Giantknife extends ItemSword{
	public YanLung_Giantknife() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("yanlung_giantknife");
		setMaxStackSize(1);
		setMaxDamage(1500);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
