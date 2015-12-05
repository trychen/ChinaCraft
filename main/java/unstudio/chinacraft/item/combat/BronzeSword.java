package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.common.ChinaCraft;

public class BronzeSword  extends ItemSword{
	public BronzeSword() {
		super(ChinaCraft.BRONZE);
		setUnlocalizedName("bronze_sword");
		setCreativeTab(ChinaCraft.tabTool);

	}
}
