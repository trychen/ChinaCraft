package unstudio.chinacraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.tileentity.TileCooker;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TilePotteryKiln;
import unstudio.chinacraft.util.BlockRule;
import unstudio.chinacraft.util.BlocksChecker;
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
	
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

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
    
	 public void breakBlock(World World, int x, int y, int z, Block Block, int var1)
	    {
		 TilePotteryKiln tileentity = (TilePotteryKiln) World.getTileEntity(x, y, z);
		 Random random = World.rand;
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
	                            EntityItem entityitem = new EntityItem(World, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

	                            if (itemstack.hasTagCompound())
	                            {
	                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
	                            }

	                            float f3 = 0.05F;
	                            entityitem.motionX = (double)((float)random.nextGaussian() * f3);
	                            entityitem.motionY = (double)((float)random.nextGaussian() * f3 + 0.2F);
	                            entityitem.motionZ = (double)((float)random.nextGaussian() * f3);
	                            World.spawnEntityInWorld(entityitem);
	                        }
	                    }
	                }

	                World.func_147453_f(x, y, z, Block);
	            }
	        

	        super.breakBlock(World, x, y, z, Block, var1);
	    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
    }
    
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return Item.getItemFromBlock(ChinaCraft.blockPotteryKiln);
	}
	
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
    	System.out.println(type);
    	if(type == 0) {
    		world.setBlock(x, y, z-1, ChinaCraft.blockFirebrickStructure, 1, 2);
    	}else if(type == 1) {
    		world.setBlock(x+1, y, z, ChinaCraft.blockFirebrickStructure, 1, 2);
    	}else if(type == 2) {
    		world.setBlock(x, y, z+1, ChinaCraft.blockFirebrickStructure, 1, 2);
    	}else if(type == 3) {
    		world.setBlock(x-1, y, z, ChinaCraft.blockFirebrickStructure, 1, 2);
    	}
    	BlocksChecker checker = BlocksChecker.Pottery_Kiln.copy();
    	int tx=x-1;
    	int ty=y+2;
    	int tz=z-1;
		for (int Y = 0; y < checker.getHeight(); y++) {
			for (int Z = 0; z < checker.getWidthZ(); z++) {
				for (int  X= 0; x < checker.getWidthX(); x++) {
					BlockRule rule = checker.getBlockRule()[y][z][x];
					if (rule != null && !rule.check(world,X+tx, Y-ty, Z+tz)&&rule.getBlock().equals(ChinaCraft.blockFirebrick)) {
						world.setBlock(X+tx, Y-ty, Z+tz, ChinaCraft.blockFirebrickStructure, 0, 2);
						world.setTileEntity(X+tx, Y-ty, Z+tz, tile);
					}
				}
			}
		}
    }
    
    public static void destroyPotteryKiln(World world,int x,int y,int z) {
    	
    }
}
