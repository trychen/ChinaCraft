package unstudio.chinacraft.block.especial;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileSericultureFrame;

import java.util.Random;


public class BlockSericultureFrame extends BlockContainer {

    public BlockSericultureFrame() {
        super(Material.wood);
        setUnlocalizedName("sericulture_frame");
        setHardness(1.0F);
        setResistance(5.0F);
        setCreativeTab(ChinaCraft.tabFarming);
        setSoundType(SoundType.Wood);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ChinaCraft.sericultureFrame);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(ChinaCraft.sericultureFrame);
    }

    /*@Override
    public void registerBlockIcons(IIconRegister iIconRegister) {
        this.blockIcon = Blocks.sapling.getIcon(0,0);
    }*/

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileSericultureFrame();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        playerIn.openGui(ChinaCraft.instance, GuiID.GUI_Sericulture_Farme, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileSericultureFrame tileentity = (TileSericultureFrame) worldIn.getTileEntity(pos);
        Random random = worldIn.rand;
        if (tileentity != null) {
            InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);

            worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        }

        super.breakBlock(worldIn, pos, state);
    }
}
