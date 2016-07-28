package unstudio.sinocraft.block.model;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import unstudio.sinocraft.common.SinoCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCCLantern extends Block {

    private IIcon side, top;

    public BlockCCLantern() {
        super(Material.wood);
        setBlockName("lantern");
        setCreativeTab(SinoCraft.tabCore);
        setLightLevel(1.0F);
        setStepSound(soundTypeWood);
    }

    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_,
            AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F * 3, 0.0625F * 13, 1.0F, 0.0625F * 13);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_,
                p_149743_7_);
        this.setBlockBoundsForItemRender();
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F * 3, 0.0625F * 13, 1.0F, 0.0625F * 13);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.top = reg.registerIcon("sinocraft:lantern_top");
        this.side = reg.registerIcon("sinocraft:lantern");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i1, int i2) {
        if (i1 == 0 || i1 == 1)
            return top;
        else
            return side;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconTop() {
        return top;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconSide() {
        return side;
    }
}
