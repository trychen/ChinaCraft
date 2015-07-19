package unstudio.chinacraft.combat;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeLeggings extends ItemArmor{

	public BronzeLeggings() {
		super(ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,2);
		setUnlocalizedName("bronze_legs");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
