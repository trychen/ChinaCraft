package unstudio.chinacraft.item;

import unstudio.chinacraft.common.ChinaCraft;
import net.minecraft.item.ItemSword;

public class ItemArtKnife extends ItemSword{

	public ItemArtKnife() {
		super(ToolMaterial.WOOD);
		setUnlocalizedName("art_knife");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
