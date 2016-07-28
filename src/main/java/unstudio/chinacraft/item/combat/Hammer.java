package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemSword;

import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.ClientProxy;
import unstudio.chinacraft.util.annotation.register.IClient;

public class Hammer extends ItemSword implements IClient{
    private float damageVsEntity;

    public Hammer(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
        setCreativeTab(ChinaCraft.tabTool);
        damageVsEntity = 1600.0f;
    }

    @Override
    public void clientInit() {
        MinecraftForgeClient.registerItemRenderer(this, ClientProxy.spearItemRender);
    }
}
