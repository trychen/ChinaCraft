package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeBoots extends ItemArmor {

    public BronzeBoots() {
        super(ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture, 3);
        setUnlocalizedName("bronze_boots");
        setMaxStackSize(1);
        setCreativeTab(SinoCraft.tabTool);
    }
}
