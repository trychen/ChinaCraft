package unstudio.chinacraft.block.especial;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import unstudio.chinacraft.tileentity.TilePotteryBase;

import java.util.Random;

public class BlockPotteryBase extends BlockContainer {

    private String PotteryType;

    public BlockPotteryBase() {
        super(Material.rock);
        setUnlocalizedName("pottery");
        setHardness(0.5F);
        setResistance(5.0F);
        setLightLevel(0.0F);
        setSoundType(SoundType.Stone);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TilePotteryBase();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
        TilePotteryBase tileentity = (TilePotteryBase) worldIn.getTileEntity(pos);
        if (stack.hasTagCompound()) {
            tileentity.setPotteryType(stack.getTagCompound().getString("PotteryType"));
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if(!(tileentity instanceof TilePotteryBase)) return;
        Random random = worldIn.rand;
        if (tileentity != null) {
            ItemStack itemstack = new ItemStack(this);
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setString("PotteryType", ((TilePotteryBase)tileentity).getPotteryType());
            itemstack.setTagCompound(nbtTagCompound);
            if (itemstack != null) {
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;
                EntityItem entityitem = new EntityItem(worldIn, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2,
                        new ItemStack(itemstack.getItem(), itemstack.stackSize, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float) random.nextGaussian() * f3;
                entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float) random.nextGaussian() * f3;
                worldIn.spawnEntityInWorld(entityitem);
            }
        }
        worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        super.breakBlock(worldIn, pos, state);
    }

    public String getPotteryType() {
        return PotteryType;
    }

    public void setPotteryType(String potteryType) {
        PotteryType = potteryType;
    }
}
