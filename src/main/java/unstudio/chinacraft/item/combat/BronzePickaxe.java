package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemPickaxe;

import unstudio.chinacraft.common.ChinaCraft;

public class BronzePickaxe extends ItemPickaxe {

    public BronzePickaxe() {
        super(ChinaCraft.BRONZE);
        setUnlocalizedName("bronze_pickaxe");
        setCreativeTab(ChinaCraft.tabTool);
    }
}
