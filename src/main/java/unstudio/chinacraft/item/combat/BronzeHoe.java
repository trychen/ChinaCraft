package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemHoe;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeHoe extends ItemHoe {
    public BronzeHoe() {
        super(ToolMaterial.IRON);
        setUnlocalizedName("bronze_hoe");
        setMaxStackSize(1);
        setMaxDamage(251);
        setCreativeTab(SinoCraft.tabTool);
    }
}
