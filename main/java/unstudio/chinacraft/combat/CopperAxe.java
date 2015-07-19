package unstudio.chinacraft.combat;

import net.minecraft.item.ItemAxe;
import unstudio.chinacraft.ChinaCraft;

public class CopperAxe extends ItemAxe{

	public CopperAxe() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("copper_axe");
		setMaxStackSize(1);
		setMaxDamage(200);
		setCreativeTab(ChinaCraft.tabCore);
	}

}
