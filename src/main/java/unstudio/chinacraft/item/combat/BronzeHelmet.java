package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.chinacraft.common.ChinaCraft;

public class BronzeHelmet extends ItemArmor {

    public BronzeHelmet() {
        super(ArmorMaterial.IRON, ChinaCraft.bronzeArmorTexture, 0);
        setUnlocalizedName("bronze_helmet");
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
    }

}
