package unstudio.chinacraft.item.combat;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.client.resources.I18n;

import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.ClientProxy;
import unstudio.chinacraft.util.annotation.register.IClient;

public class BronzeBroadSword extends ItemSword implements IClient{
    private String jade = null;

    public BronzeBroadSword(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        setUnlocalizedName("bronze_bigsword");
        if (name != null)
            jade = name;
        setMaxStackSize(1);
        setMaxDamage(500);
        setCreativeTab(ChinaCraft.tabTool);
    }

    @Override
    public String getItemStackDisplayName(ItemStack p_77653_1_) {
        if (jade != null) {
            return super.getItemStackDisplayName(p_77653_1_) + " - "
                    + I18n.format("item.jade_" + jade + ".name");
        }
        return super.getItemStackDisplayName(p_77653_1_);
    }

    @Override
    public void clientInit() {
        MinecraftForgeClient.registerItemRenderer(this, ClientProxy.hugeItemRenderer);
    }
}
