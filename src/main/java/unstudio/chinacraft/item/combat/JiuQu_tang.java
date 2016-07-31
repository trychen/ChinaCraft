package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;

import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

/**
 * Created by Huangshize .
 */
public class JiuQu_tang extends ItemSword implements ISpecialEquippedRender{

    public JiuQu_tang() {
        super(ToolMaterial.IRON);
        setUnlocalizedName("jiuqu_tang");
        setMaxStackSize(1);
        setMaxDamage(251);
        setCreativeTab(ChinaCraft.tabTool);
    }

    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.spear;
    }
}
