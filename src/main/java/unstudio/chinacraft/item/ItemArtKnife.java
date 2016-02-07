package unstudio.chinacraft.item;

import net.minecraft.item.ItemSword;

import unstudio.chinacraft.common.ChinaCraft;

public class ItemArtKnife extends ItemSword {

    public ItemArtKnife() {
        super(ToolMaterial.WOOD);
        setUnlocalizedName("art_knife");
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
    }

}
