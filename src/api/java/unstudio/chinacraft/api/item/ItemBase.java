package unstudio.chinacraft.api.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {

    private IIcon icon;

    @Override
    public IIcon getIconFromDamage(int p_77617_1_) {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        icon = p_94581_1_.registerIcon(getIconString());
    }
}
