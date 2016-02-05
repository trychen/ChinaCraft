package unstudio.chinacraft.block.especial;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileEntityInstruments;

import java.util.Random;

/**
 * Created by trych on 2016/1/27.
 */
public class CCInstruments extends BlockContainer {
    private boolean isAlignmentMusic;
    private String musicname;
    private int maxMusic;

    /**
     * 乐器的父类
     * @param name 乐器名称
     * @param material 乐器材质
     * @param isAlignmentMusic 是否为序列化的音乐
     * @param musicname soundname
     * @param maxMusic 最大的音乐数
     * @throws CouldNotSerializeMusicNameException 不能把音乐序列化时抛出该错误
     */
    public CCInstruments(String name,Material material,boolean isAlignmentMusic,String musicname,int maxMusic){
        super(material);
        setBlockName(name);
        this.musicname = musicname;
        this.isAlignmentMusic=isAlignmentMusic;
        this.setCreativeTab(ChinaCraft.tabCore);
    }

    public boolean isAlignmentMusic(){
        return isAlignmentMusic;
    }

    public String getMusicname(){
        return musicname;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityInstruments(maxMusic);
    }

    public static class CouldNotSerializeMusicNameException extends Exception {

    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote)
        {
            if (isAlignmentMusic()) {
                TileEntityInstruments tileEntity = (TileEntityInstruments) world.getTileEntity(x, y, z);
                tileEntity.getMusicCount();
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, this.getMusicname(), 3.0F, 10f);
                tileEntity.changeMusicCount();
            } else
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, this.getMusicname(), 3.0F, 10f);
        }
        return true;
    }

}