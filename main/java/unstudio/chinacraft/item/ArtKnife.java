package unstudio.chinacraft.item;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.item.ItemSword;

public class ArtKnife extends ItemSword{

	public ArtKnife() {
		super(ToolMaterial.WOOD);
		setUnlocalizedName("art_knife");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
