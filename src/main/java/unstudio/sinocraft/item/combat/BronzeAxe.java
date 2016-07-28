package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeAxe extends ItemAxe {

    public BronzeAxe() {
        super(SinoCraft.BRONZE);
        setUnlocalizedName("bronze_axe");
        setMaxStackSize(1);
        setMaxDamage(251);
        setCreativeTab(SinoCraft.tabTool);
    }

    @Override
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }
}
