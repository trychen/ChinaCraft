package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemSword;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeSword extends ItemSword {
    public BronzeSword() {
        super(SinoCraft.BRONZE);
        setUnlocalizedName("bronze_sword");
        setCreativeTab(SinoCraft.tabTool);

    }
}
