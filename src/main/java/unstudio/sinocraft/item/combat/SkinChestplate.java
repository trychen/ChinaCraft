package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemArmor;

import unstudio.sinocraft.common.SinoCraft;

/**
 * Created by Trychen on 2015/11/7.
 */
public class SkinChestplate extends ItemArmor {
    private String TextureName = "";

    public SkinChestplate(ArmorMaterial armorMaterial, String name, String textureName) {
        super(armorMaterial, 1, 1);
        setUnlocalizedName(name);
        this.TextureName = textureName;
        setCreativeTab(SinoCraft.tabTool);
    }
}
