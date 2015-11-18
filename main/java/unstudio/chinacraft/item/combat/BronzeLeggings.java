package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemArmor;
import unstudio.chinacraft.ChinaCraft;

public class BronzeLeggings extends ItemArmor{

	public BronzeLeggings() {
		super(ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,2);
		setUnlocalizedName("bronze_legs");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
