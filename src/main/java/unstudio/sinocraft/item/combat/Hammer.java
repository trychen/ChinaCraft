package unstudio.sinocraft.item.combat;

import net.minecraft.item.ItemSword;

import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.common.ClientProxy;
import unstudio.sinocraft.util.annotation.register.IClient;

public class Hammer extends ItemSword implements IClient{
    private float damageVsEntity;

    public Hammer(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        setUnlocalizedName("hammer_" + name);
        setCreativeTab(SinoCraft.tabTool);
        damageVsEntity = 1600.0f;
    }

    @Override
    public void clientInit() {
        MinecraftForgeClient.registerItemRenderer(this, ClientProxy.spearItemRender);
    }
}
