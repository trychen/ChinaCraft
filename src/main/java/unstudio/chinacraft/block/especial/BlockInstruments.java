package unstudio.chinacraft.block.especial;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileEntityInstruments;

import java.util.Random;

/**
 * Created by trych on 2016/1/27.
 */
public class BlockInstruments extends BlockContainer {
    private boolean isAlignmentMusic;
    private String musicname;
    private int maxMusic;

    /**
     * 乐器的父类
     * 
     * @param name
     *            乐器名称
     * @param material
     *            乐器材质
     * @param isAlignmentMusic
     *            是否为序列化的音乐
     * @param musicname
     *            soundname
     * @param maxMusic
     *            最大的音乐数
     * @throws CouldNotSerializeMusicNameException
     *             不能把音乐序列化时抛出该错误
     */
    public BlockInstruments(String name, Material material, boolean isAlignmentMusic, String musicname, int maxMusic) {
        super(material);
        setUnlocalizedName(name);
        this.musicname = musicname;
        this.isAlignmentMusic = isAlignmentMusic;
        this.setCreativeTab(ChinaCraft.tabCore);
    }

    public boolean isAlignmentMusic() {
        return isAlignmentMusic;
    }

    public String getMusicname() {
        return musicname;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityInstruments(maxMusic);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	// TODO Auto-generated method stub
    	super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (isAlignmentMusic()) {
                TileEntityInstruments tileEntity = (TileEntityInstruments) worldIn.getTileEntity(pos);
                tileEntity.getMusicCount();
                worldIn.playSoundEffect((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, this.getMusicname(),
                        3.0F, 10f);
                tileEntity.changeMusicCount();
            } else
                worldIn.playSoundEffect((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, this.getMusicname(),
                        3.0F, 10f);
        }
        return true;
    }

    public static class CouldNotSerializeMusicNameException extends Exception {

    }

}
