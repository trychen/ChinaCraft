package unstudio.chinacraft.item.combat;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class BronzeHelmet extends ItemArmor{

	public BronzeHelmet() {
		super(ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,0);
		setUnlocalizedName("bronze_helmet");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}

}
