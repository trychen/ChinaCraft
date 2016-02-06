package unstudio.chinacraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSilk extends Item {

    public static final String[] silk_name = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan",
            "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
    private IIcon[] silk_icon;

    public ItemSilk() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ChinaCraft.tabPlant);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        int j = MathHelper.clamp_int(p_77617_1_, 0, 15);
        return this.silk_icon[j];
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + silk_name[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < 16; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        this.silk_icon = new IIcon[silk_name.length];

        for (int i = 0; i < silk_name.length; ++i) {
            this.silk_icon[i] = p_94581_1_.registerIcon("chinacraft:silk_" + silk_name[i]);
        }
    }
}
