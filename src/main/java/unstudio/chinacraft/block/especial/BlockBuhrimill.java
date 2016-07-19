package unstudio.chinacraft.block.especial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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
import unstudio.chinacraft.tileentity.TileBuhrimill;

public class BlockBuhrimill extends BlockContainer {

    public BlockBuhrimill() {
        super(Material.rock);
        setUnlocalizedName("buhrimill");
        setHardness(3.0F);
        setResistance(10.0F);
        setCreativeTab(ChinaCraft.tabCore);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 1);
    }

    // @Override
    // public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
    // int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
    // List p_149743_6_, Entity p_149743_7_) {
    // this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    // super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
    // p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    // this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 0.875F, 0.75F);
    // super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
    // p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    // this.setBlockBoundsForItemRender();
    // }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
        int l = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(4));
        }

        if (l == 1) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(5));
        }

        if (l == 2) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(2));
        }

        if (l == 3) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(3));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ChinaCraft.buhrimill);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(ChinaCraft.buhrimill);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileBuhrimill();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            if (worldIn.isRemote)
                return true;
            playerIn.openGui(ChinaCraft.instance, GuiID.GUI_Buhrimill, worldIn, pos.getX(), pos.getY(), pos.getZ());
        } else {
            if (worldIn.getTileEntity(pos) instanceof TileBuhrimill) {
                ((TileBuhrimill) worldIn.getTileEntity(pos)).addAngle(10);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileBuhrimill tileentity = (TileBuhrimill) worldIn.getTileEntity(pos);
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
                        EntityItem entityitem = new EntityItem(worldIn, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2,
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

        super.breakBlock(worldIn, pos, state);
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon("Minecraft:stone");
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }*/
}
