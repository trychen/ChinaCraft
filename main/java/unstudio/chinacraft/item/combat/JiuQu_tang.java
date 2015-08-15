package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import unstudio.chinacraft.ChinaCraft;

public class JiuQu_tang extends ItemSword{

	public JiuQu_tang() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("jiuqu_tang");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
