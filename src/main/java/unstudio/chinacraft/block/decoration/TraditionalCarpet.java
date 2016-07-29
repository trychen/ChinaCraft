package unstudio.chinacraft.block.decoration;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.List;


public class TraditionalCarpet extends BlockCarpet {
    private String texture = "";

    public TraditionalCarpet(String name, String texture) {
        setUnlocalizedName(name);
        this.texture = texture;
        setCreativeTab(ChinaCraft.tabCore);
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {

        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.texture);
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {}
}
