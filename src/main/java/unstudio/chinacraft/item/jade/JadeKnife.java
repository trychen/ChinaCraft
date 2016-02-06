package unstudio.chinacraft.item.jade;

import unstudio.chinacraft.common.ChinaCraft;
import net.minecraft.item.ItemSword;

public class JadeKnife extends ItemSword{

	public JadeKnife() {
		super(ToolMaterial.GOLD);
		setUnlocalizedName("jade_knife");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
