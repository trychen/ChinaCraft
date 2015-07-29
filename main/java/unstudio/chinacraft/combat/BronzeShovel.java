package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeShovel extends ItemSpade{

	public BronzeShovel() {
		super(ChinaCraft.BRONZE);
		setUnlocalizedName("bronze_pickaxe");
		setCreativeTab(ChinaCraft.tabTool);
	}
}
