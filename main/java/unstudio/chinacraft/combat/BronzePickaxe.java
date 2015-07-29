package unstudio.chinacraft.combat;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzePickaxe extends ItemPickaxe{

	public BronzePickaxe() {
		super(ChinaCraft.BRONZE);
		setUnlocalizedName("bronze_pickaxe");
		setCreativeTab(ChinaCraft.tabTool);
	}
}
