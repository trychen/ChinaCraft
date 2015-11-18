package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemArmor;
import unstudio.chinacraft.ChinaCraft;

public class BronzeBoots extends ItemArmor{

	public BronzeBoots() {
		super(ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,3);
		setUnlocalizedName("bronze_boots");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
