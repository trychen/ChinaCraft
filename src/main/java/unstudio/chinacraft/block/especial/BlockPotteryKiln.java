package unstudio.chinacraft.block.especial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TilePotteryKiln;

public class BlockPotteryKiln extends BlockContainer {

    public BlockPotteryKiln() {
        super(Material.rock);
        setUnlocalizedName("firebrick");
        setHardness(1.5F);
        setResistance(10.0F);
        setLightLevel(0.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 0);
    }
    public static void generatePotteryKiln(World world, BlockPos pos, int type) {
        TileFirebrickStructure tile = (TileFirebrickStructure) ChinaCraft.blockFirebrickStructure
                .createNewTileEntity(world, 0);
        tile.setPosition(pos);
        world.setBlockState(pos, ChinaCraft.blockPotteryKiln.getStateFromMeta(type), 2);
        if (type == 0) {
            world.setBlockState(pos.north(), ChinaCraft.blockFirebrickStructure.getStateFromMeta(1), 2);
            ((TileFirebrickStructure) world.getTileEntity(pos.north())).setPosition(pos);
        } else if (type == 1) {
            world.setBlockState(pos.east(), ChinaCraft.blockFirebrickStructure.getStateFromMeta(1), 2);
            ((TileFirebrickStructure) world.getTileEntity(pos.east())).setPosition(pos);
        } else if (type == 2) {
            world.setBlockState(pos.south(), ChinaCraft.blockFirebrickStructure.getStateFromMeta(1), 2);
            ((TileFirebrickStructure) world.getTileEntity(pos.south())).setPosition(pos);
        } else if (type == 3) {
            world.setBlockState(pos.west(), ChinaCraft.blockFirebrickStructure.getStateFromMeta(1), 2);
            ((TileFirebrickStructure) world.getTileEntity(pos.west())).setPosition(pos);
        }
        pos = pos.west().north();
        for (int Y = 0; Y < 3; Y++) {
            for (int Z = 0; Z < 3; Z++) {
                for (int X = 0; X < 3; X++) {
                    if (world.getBlockState(pos.add(X, Y, Z)).getBlock().equals(ChinaCraft.blockFirebrick)) {
                        world.setBlockState(pos.add(X, Y, Z), ChinaCraft.blockFirebrickStructure.getStateFromMeta(0), 2);
                        ((TileFirebrickStructure) world.getTileEntity(pos.add(X, Y, Z))).setPosition(pos.south().east());
                    }
                }
            }
        }
    }

    public static void destroyPotteryKiln(World world, BlockPos pos) {
        pos = pos.west().north();
        for (int Y = 0; Y < 3; Y++) {
            for (int Z = 0; Z < 3; Z++) {
                for (int X = 0; X < 3; X++) {
                    if (world.getBlockState(pos.add(X, Y, Z)).getBlock().equals(ChinaCraft.blockFirebrickStructure)
                            || world.getBlockState(pos.add(X, Y, Z)).getBlock().equals(ChinaCraft.blockPotteryKiln)) {
                        world.setBlockState(pos.add(X, Y, Z), ChinaCraft.blockFirebrick.getDefaultState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
        int l = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0) {
        	worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(2));
        }

        if (l == 1) {
        	worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(5));
        }

        if (l == 2) {
        	worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(3));
        }

        if (l == 3) {
        	worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(4));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TilePotteryKiln();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TilePotteryKiln tileentity = (TilePotteryKiln) worldIn.getTileEntity(pos);
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
                                pos.getZ() + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

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
        BlockPotteryKiln.destroyPotteryKiln(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory(
                (IInventory) worldIn.getTileEntity(pos));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ChinaCraft.blockPotteryKiln);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        playerIn.openGui(ChinaCraft.instance, GuiID.GUI_PotteryKiln, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
