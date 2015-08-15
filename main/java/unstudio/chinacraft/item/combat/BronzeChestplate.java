package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import unstudio.chinacraft.ChinaCraft;

public class BronzeChestplate extends ItemArmor{

	public BronzeChestplate() {
		super(ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,1);
		setUnlocalizedName("bronze_body");
		setMaxStackSize(1);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
