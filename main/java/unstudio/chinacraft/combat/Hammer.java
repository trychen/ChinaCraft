package unstudio.chinacraft.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class Hammer extends ItemSword {
    private float damageVsEntity;
    public Hammer(ToolMaterial toolMaterial,String name){
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
        setCreativeTab(ChinaCraft.tabTool);
        damageVsEntity = 2000.0f;
    }
}
