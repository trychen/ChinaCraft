package unstudio.chinacraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by AAA on 2016/2/13.
 */
public class BlockCCLog extends BlockLog {

    private IIcon top, side;
    private String topTexture, sideTexture;

    public BlockCCLog(String topTexture, String sideTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
    }

    public Block setTopTextureName(String arg1) {
        this.topTexture = arg1;
        return this;
    }

    public Block setSideTextureName(String arg1) {
        this.sideTexture = arg1;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.top = reg.registerIcon(topTexture);
        this.side = reg.registerIcon(sideTexture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_) {
        return side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_) {
        return top;
    }
}
