package unstudio.chinacraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TilePotteryKiln;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPotteryKiln extends BlockContainer{
	
	public BlockPotteryKiln() {
		super(Material.rock);
		setBlockName("firebrick");
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 0);
	}
	
    @Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePotteryKiln();
	}
    
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_,int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
			TilePotteryKiln tileentity = (TilePotteryKiln) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
			 Random random = p_149749_1_.rand;
	            if (tileentity != null)
	            {
	                for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1)
	                {
	                    ItemStack itemstack = tileentity.getStackInSlot(i1);

	                    if (itemstack != null)
	                    {
	                        float f = random.nextFloat() * 0.8F + 0.1F;
	                        float f1 = random.nextFloat() * 0.8F + 0.1F;
	                        float f2 = random.nextFloat() * 0.8F + 0.1F;

	                        while (itemstack.stackSize > 0)
	                        {
	                            int j1 = random.nextInt(21) + 10;

	                            if (j1 > itemstack.stackSize)
	                            {
	                                j1 = itemstack.stackSize;
	                            }

	                            itemstack.stackSize -= j1;
	                            EntityItem entityitem = new EntityItem(p_149749_1_, p_149749_2_ + f, p_149749_3_ + f1, p_149749_4_ + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

	                            if (itemstack.hasTagCompound())
	                            {
	                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
	                            }

	                            float f3 = 0.05F;
	                            entityitem.motionX = (float)random.nextGaussian() * f3;
	                            entityitem.motionY = (float)random.nextGaussian() * f3 + 0.2F;
	                            entityitem.motionZ = (float)random.nextGaussian() * f3;
	                            p_149749_1_.spawnEntityInWorld(entityitem);
	                        }
	                    }
	                }
	                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
	            }
		BlockPotteryKiln.destroyPotteryKiln(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_);
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,p_149749_5_, p_149749_6_);
	}

    @Override
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }
    
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return Item.getItemFromBlock(ChinaCraft.blockPotteryKiln);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(world.isRemote) return true;
            p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_PotteryKiln, world, x, y, z);
            return true;
    }
    
    public static void generatePotteryKiln(World world,int x,int y,int z,int type) {
    	TileFirebrickStructure tile = (TileFirebrickStructure) ChinaCraft.blockFirebrickStructure.createNewTileEntity(world, 0);
    	tile.setPosition(x, y, z);
    	world.setBlock(x, y, z, ChinaCraft.blockPotteryKiln, type, 2);
    	if(type == 0) {
    		world.setBlock(x, y, z-1, ChinaCraft.blockFirebrickStructure, 1, 2);
    		((TileFirebrickStructure) world.getTileEntity(x, y, z-1)).setPosition(x, y, z);
    	}else if(type == 1) {
    		world.setBlock(x+1, y, z, ChinaCraft.blockFirebrickStructure, 1, 2);
    		((TileFirebrickStructure) world.getTileEntity(x+1, y, z)).setPosition(x, y, z);
    	}else if(type == 2) {
    		world.setBlock(x, y, z+1, ChinaCraft.blockFirebrickStructure, 1, 2);
    		((TileFirebrickStructure) world.getTileEntity(x, y, z+1)).setPosition(x, y, z);
    	}else if(type == 3) {
    		world.setBlock(x-1, y, z, ChinaCraft.blockFirebrickStructure, 1, 2);
    		((TileFirebrickStructure) world.getTileEntity(x-1, y, z)).setPosition(x, y, z);
    	}
    	int tx=x-1;
    	int ty=y;
    	int tz=z-1;
		for (int Y = 0; Y < 3; Y++) {
			for (int Z = 0; Z < 3; Z++) {
				for (int  X= 0; X < 3; X++) {
					if (world.getBlock(X+tx, ty+Y, Z+tz).equals(ChinaCraft.blockFirebrick)) {
						world.setBlock(X+tx, ty+Y, Z+tz, ChinaCraft.blockFirebrickStructure, 0, 2);
						((TileFirebrickStructure) world.getTileEntity(X+tx, ty+Y, Z+tz)).setPosition(x, y, z);
					}
				}
			}
		}
    }
    
    public static void destroyPotteryKiln(World world,int x,int y,int z) {
    	int tx=x-1;
    	int ty=y;
    	int tz=z-1;
		for (int Y = 0; Y < 3; Y++) {
			for (int Z = 0; Z < 3; Z++) {
				for (int  X= 0; X < 3; X++) {
					if (world.getBlock(X+tx, ty+Y, Z+tz).equals(ChinaCraft.blockFirebrickStructure)||world.getBlock(X+tx, ty+Y, Z+tz).equals(ChinaCraft.blockPotteryKiln)) {
						world.setBlock(X+tx, ty+Y, Z+tz, ChinaCraft.blockFirebrick, 0, 2);
					}
				}
			}
		}
    }
}
