package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemAxe;
import unstudio.chinacraft.ChinaCraft;

public class BronzeAxe extends ItemAxe{

	public BronzeAxe() {
		super(ChinaCraft.BRONZE);
		setUnlocalizedName("bronze_axe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
