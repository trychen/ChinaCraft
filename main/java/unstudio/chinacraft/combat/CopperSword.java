package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class CopperSword  extends ItemSword{
	public CopperSword() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("copper_sword");
		setMaxStackSize(1);
		setMaxDamage(200);
		setCreativeTab(ChinaCraft.tabCore);
	}
}
