package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;

public class BronzeAxe extends ItemAxe{

	public BronzeAxe() {
		super(ChinaCraft.BRONZE);
		setUnlocalizedName("bronze_axe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		return false;
	}
}
