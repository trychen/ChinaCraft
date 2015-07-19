package unstudio.chinacraft.combat;

import net.minecraft.item.ItemAxe;
import unstudio.chinacraft.ChinaCraft;

public class BronzeAxe extends ItemAxe{

	public BronzeAxe() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_axe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
