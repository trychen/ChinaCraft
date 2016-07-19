package unstudio.chinacraft.block.model;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;

public class BlockCCLantern extends Block {


    public BlockCCLantern() {
        super(Material.wood);
        setUnlocalizedName("lantern");
        setCreativeTab(ChinaCraft.tabCore);
        setLightLevel(1.0F);
        setStepSound(soundTypeWood);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask,
    		List<AxisAlignedBB> list, Entity collidingEntity) {
        this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F * 3, 0.0625F * 13, 1.0F, 0.0625F * 13);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
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
