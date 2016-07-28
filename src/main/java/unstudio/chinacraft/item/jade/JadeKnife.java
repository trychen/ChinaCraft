package unstudio.chinacraft.item.jade;

import net.minecraft.item.ItemSword;

import unstudio.chinacraft.common.ChinaCraft;

public class JadeKnife extends ItemSword {

    public JadeKnife() {
        super(ToolMaterial.GOLD);
        setUnlocalizedName("jade_knife");
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
    }
}
