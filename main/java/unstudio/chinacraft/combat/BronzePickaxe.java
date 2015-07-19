package unstudio.chinacraft.combat;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzePickaxe extends ItemPickaxe{

	public BronzePickaxe() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bronze_pickaxe");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
