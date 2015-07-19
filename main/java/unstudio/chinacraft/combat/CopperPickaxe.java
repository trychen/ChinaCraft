package unstudio.chinacraft.combat;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class CopperPickaxe extends ItemPickaxe{

	public CopperPickaxe() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("copper_pickaxe");
		setMaxStackSize(1);
		setMaxDamage(200);
		setCreativeTab(ChinaCraft.tabCore);
	}
}
