package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemSpade;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeShovel extends ItemSpade {

    public BronzeShovel() {
        super(SinoCraft.BRONZE);
        setUnlocalizedName("bronze_pickaxe");
        setCreativeTab(SinoCraft.tabTool);
    }
}
