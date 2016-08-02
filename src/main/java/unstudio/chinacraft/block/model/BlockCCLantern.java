package unstudio.chinacraft.block.model;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCCLantern extends Block {


    public BlockCCLantern() {
        super(Material.WOOD);
        setUnlocalizedName("lantern");
        setCreativeTab(ChinaCraft.tabCore);
        setLightLevel(1.0F);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        //TODO this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F * 3, 0.0625F * 13, 1.0F, 0.0625F * 13);

        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
        this.setBlockBoundsForItemRender();
    }

    // TODO @Override
    public void setBlockBoundsForItemRender() {
        //TODO this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F * 3, 0.0625F * 13, 1.0F, 0.0625F * 13);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    // TODO @Override
    public int getRenderType() {
        return 1;
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.top = reg.registerIcon("chinacraft:lantern_top");
        this.side = reg.registerIcon("chinacraft:lantern");
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
    }*/
}
