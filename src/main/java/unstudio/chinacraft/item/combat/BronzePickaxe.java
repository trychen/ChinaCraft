package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemPickaxe;

import unstudio.sinocraft.common.SinoCraft;

public class BronzePickaxe extends ItemPickaxe {

    public BronzePickaxe() {
        super(SinoCraft.BRONZE);
        setUnlocalizedName("bronze_pickaxe");
        setCreativeTab(SinoCraft.tabTool);
    }
}
