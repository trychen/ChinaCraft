package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class Hammer extends ItemSword {
    private float damageVsEntity;
    public Hammer(ToolMaterial toolMaterial,String name){
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
        setCreativeTab(ChinaCraft.tabTool);
        damageVsEntity = 1600.0f;
    }
}
