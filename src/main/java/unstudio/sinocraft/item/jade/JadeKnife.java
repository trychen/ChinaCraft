package unstudio.sinocraft.item.jade;

import net.minecraft.item.ItemSword;

import unstudio.sinocraft.common.SinoCraft;

public class JadeKnife extends ItemSword {

    public JadeKnife() {
        super(ToolMaterial.GOLD);
        setUnlocalizedName("jade_knife");
        setMaxStackSize(1);
        setCreativeTab(SinoCraft.tabTool);
    }
}
