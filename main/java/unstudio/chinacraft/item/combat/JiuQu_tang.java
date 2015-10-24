package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;
/**
 * Created by Huangshize .
 */
public class JiuQu_tang extends ItemSword{

	public JiuQu_tang() {
		super(ToolMaterial.IRON);
		setUnlocalizedName("jiuqu_tang");
		setMaxStackSize(1);
		setMaxDamage(251);
		setCreativeTab(ChinaCraft.tabTool);
	}
}
