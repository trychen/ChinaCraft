package unstudio.chinacraft.jade;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.item.ItemSword;

public class JadeKnife extends ItemSword{

	public JadeKnife() {
		super(ToolMaterial.WOOD);
		setUnlocalizedName("jade_knife");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
