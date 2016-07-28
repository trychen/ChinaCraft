package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeChestplate extends ItemArmor {

    public BronzeChestplate() {
        super(ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture, 1);
        setUnlocalizedName("bronze_body");
        setMaxStackSize(1);
        setCreativeTab(SinoCraft.tabTool);
    }
}
