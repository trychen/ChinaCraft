package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeHelmet extends ItemArmor {

    public BronzeHelmet() {
        super(ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture, 0);
        setUnlocalizedName("bronze_helmet");
        setMaxStackSize(1);
        setCreativeTab(SinoCraft.tabTool);
    }

}
