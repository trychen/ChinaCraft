package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;

import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.ClientProxy;
import unstudio.chinacraft.util.annotation.register.IClient;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class Hammer extends ItemSword implements ISpecialEquippedRender{

    public Hammer(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
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
