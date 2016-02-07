package unstudio.chinacraft.block.especial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import unstudio.chinacraft.api.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TilePotteryKiln;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFirebrickStructure extends BlockContainer {

    private IIcon potterykiln_off, potterykiln_on, firebrick;

    public BlockFirebrickStructure() {
        super(Material.rock);
        setBlockName("firebrick");
        setHardness(1.5F);
        setResistance(10.0F);
        setLightLevel(0.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 0);
    }

    public static void updateFurnaceBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_,
            int p_149931_4_) {
        if (p_149931_0_) {
            p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, 2, 2);
        } else {
            p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, 1, 2);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileFirebrickStructure();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_,
            float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote)
            return true;
        TileFirebrickStructure tile = (TileFirebrickStructure) world.getTileEntity(x, y, z);
        if (world.getTileEntity(tile.getX(), tile.getY(), tile.getZ()) != null)
            p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_PotteryKiln, world, tile.getX(), tile.getY(),
                    tile.getZ());
        return true;
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
            int p_149749_6_) {
        TileFirebrickStructure tile = (TileFirebrickStructure) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_,
                p_149749_4_);
        if (tile != null) {
            TilePotteryKiln tileentity = (TilePotteryKiln) p_149749_1_.getTileEntity(tile.getX(), tile.getY(),
                    tile.getZ());
            Random random = p_149749_1_.rand;
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
                            EntityItem entityitem = new EntityItem(p_149749_1_, p_149749_2_ + f, p_149749_3_ + f1,
                                    p_149749_4_ + f2,
                                    new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem()
                                        .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (float) random.nextGaussian() * f3;
                            entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                            entityitem.motionZ = (float) random.nextGaussian() * f3;
                            p_149749_1_.spawnEntityInWorld(entityitem);
                        }
                    }
                }
                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
            }
            BlockPotteryKiln.destroyPotteryKiln(p_149749_1_, tile.getX(), tile.getY(), tile.getZ());
        }
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }

    @Override
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
    }
}
