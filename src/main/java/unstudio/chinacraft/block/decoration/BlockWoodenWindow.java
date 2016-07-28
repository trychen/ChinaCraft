package unstudio.sinocraft.block.decoration;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.StatCollector;

import unstudio.sinocraft.common.SinoCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodenWindow extends BlockPane {

    public BlockWoodenWindow(String arg1, String arg2) {
        super(arg1, arg2, Material.wood, true);
        setBlockName(StatCollector.translateToLocal("wooden_window"));
        setHardness(0.3F);
        setStepSound(soundTypeWood);
        setCreativeTab(SinoCraft.tabCore);
    }

    @Override
    public int damageDropped(int p_149692_1_) {
        return p_149692_1_;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        super.registerBlockIcons(p_149651_1_);
    }
}
