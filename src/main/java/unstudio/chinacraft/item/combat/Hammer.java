package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.common.ChinaCraft;

public class Hammer extends ItemSword {

    public Hammer(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
        setCreativeTab(ChinaCraft.tabTool);
    }
}
