package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.sinocraft.common.SinoCraft;

public class BronzeLeggings extends ItemArmor {

    public BronzeLeggings() {
        super(ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture, 2);
        setUnlocalizedName("bronze_legs");
        setMaxStackSize(1);
        setCreativeTab(SinoCraft.tabTool);
    }
}
