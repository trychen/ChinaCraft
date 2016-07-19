package unstudio.chinacraft.block.especial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import unstudio.chinacraft.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TilePotteryKiln;

public class BlockFirebrickStructure extends BlockContainer {

    //private IIcon potterykiln_off, potterykiln_on, firebrick;

    public BlockFirebrickStructure() {
        super(Material.rock);
        setUnlocalizedName("firebrick");
        setHardness(1.5F);
        setResistance(10.0F);
        setLightLevel(0.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 0);
    }

    public static void updateFurnaceBlockState(boolean p_149931_0_, World worldIn, BlockPos pos) {
        if (p_149931_0_) {
        	worldIn.setBlockState(pos, worldIn.getBlockState(pos).getBlock().getStateFromMeta(2));
        } else {
        	worldIn.setBlockState(pos, worldIn.getBlockState(pos).getBlock().getStateFromMeta(1));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileFirebrickStructure();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        TileFirebrickStructure tile = (TileFirebrickStructure) worldIn.getTileEntity(pos);
        if (worldIn.getTileEntity(tile.getTilePos()) != null)
            playerIn.openGui(ChinaCraft.instance, GuiID.GUI_PotteryKiln, worldIn, tile.getTilePos().getX(), tile.getTilePos().getY(),
                    tile.getTilePos().getZ());
        return true;
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileFirebrickStructure tile = (TileFirebrickStructure) worldIn.getTileEntity(pos);
        if (tile != null) {
            TilePotteryKiln tileentity = (TilePotteryKiln) worldIn.getTileEntity(tile.getTilePos());
            Random random = worldIn.rand;
            if (tileentity != null) {
                for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1) {
                    ItemStack itemstack = tileentity.getStackInSlot(i1);

                    if (itemstack != null) {
                        float f = random.nextFloat() * 0.8F + 0.1F;
                        float f1 = random.nextFloat() * 0.8F + 0.1F;
                        float f2 = random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j1 = random.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize) {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(worldIn, pos.getX() + f, pos.getY() + f1,
                                    pos.getZ() + f2,
                                    new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem()
                                        .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (float) random.nextGaussian() * f3;
                            entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                            entityitem.motionZ = (float) random.nextGaussian() * f3;
                            worldIn.spawnEntityInWorld(entityitem);
                        }
                    }
                }
                worldIn.updateComparatorOutputLevel(pos, state.getBlock());
            }
            BlockPotteryKiln.destroyPotteryKiln(worldIn, tile.getTilePos());
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }

   /* @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        firebrick = p_149651_1_.registerIcon("ChinaCraft:firebrick");
        potterykiln_off = p_149651_1_.registerIcon("ChinaCraft:potterykiln_off");
        potterykiln_on = p_149651_1_.registerIcon("ChinaCraft:potterykiln_on");
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (p_149691_2_ == 0)
            return firebrick;
        else if (p_149691_2_ == 1 && p_149691_1_ != 0 && p_149691_1_ != 1)
            return potterykiln_off;
        else if (p_149691_2_ == 2 && p_149691_1_ != 0 && p_149691_1_ != 1)
            return potterykiln_on;
        else
            return firebrick;
    }*/
}
