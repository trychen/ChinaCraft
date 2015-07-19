package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class BronzeSword  extends ItemSword{
	public BronzeSword() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_sword");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
