package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSpade;

import unstudio.chinacraft.common.ChinaCraft;

public class BronzeShovel extends ItemSpade {

    public BronzeShovel() {
        super(ChinaCraft.BRONZE);
        setUnlocalizedName("bronze_pickaxe");
        setCreativeTab(ChinaCraft.tabTool);
    }
}
