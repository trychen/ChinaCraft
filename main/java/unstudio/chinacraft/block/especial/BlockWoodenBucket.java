package unstudio.chinacraft.block.especial;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.client.block.render.BlockWoodenBucketRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWoodenBucket extends Block{

	private IIcon top,bottom,side,inner;
	
	public BlockWoodenBucket() {
		super(Material.wood);
		setHardness(0.5F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
		setBlockName("wooden_bucket");
	}
	
    @Override
	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBoundsForItemRender();
    }
    
    @Override
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
	public int getRenderType()
    {
        return BlockWoodenBucketRenderer.renderID;
    }
    
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    } 
    
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int par2) {
		if (i == 0)
			return bottom;
		else if (i == 1)
			return top;
		else
			return side;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getInner() {
		return inner;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon("chinacraft:woodenbucket_top");
		this.bottom = reg.registerIcon("chinacraft:woodenbucket_bottom");
		this.side= reg.registerIcon("chinacraft:woodenbucket_side");
		this.inner = reg.registerIcon("chinacraft:woodenbucket_inner");
	}
    
    @Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	if(p_149650_1_ == 1) {
    		return ChinaCraft.woodenBucket_Water;
    	}
        return ChinaCraft.woodenBucket;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return ChinaCraft.woodenBucket;
    }
    
    @Override
	public void fillWithRain(World p_149639_1_, int p_149639_2_, int p_149639_3_, int p_149639_4_)
    {
        if (p_149639_1_.rand.nextInt(20) == 1)
        {
            int l = p_149639_1_.getBlockMetadata(p_149639_2_, p_149639_3_, p_149639_4_);

            if (l == 0)
            {
                p_149639_1_.setBlockMetadataWithNotify(p_149639_2_, p_149639_3_, p_149639_4_, 1, 2);
            }
        }
    }
    
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		ItemStack item = player.inventory.getCurrentItem();
		if (item == null)
			return true;
		if (world.getBlockMetadata(x, y, z) == 1) {
			if (item.getItem() == Items.bucket) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				if (!player.capabilities.isCreativeMode) {
					if (--item.stackSize <= 0) {
						player.inventory.setInventorySlotContents(
								player.inventory.currentItem, new ItemStack(
										Items.water_bucket));
					}else {
					player.inventory.addItemStackToInventory(new ItemStack(
							Items.water_bucket));
					}
				}
				return true;
			} else if (item.getItem() == ChinaCraft.woodenBucket) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				if (!player.capabilities.isCreativeMode) {
					if (--item.stackSize <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(
								ChinaCraft.woodenBucket_Water));
					}else {
					player.inventory.addItemStackToInventory(new ItemStack(
							ChinaCraft.woodenBucket_Water));
					}
				}
				return true;
			} else {
				return true;
			}
		} else {
			if (item.getItem() == Items.water_bucket) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2);
				if (!player.capabilities.isCreativeMode) {
					if (--item.stackSize <= 0) {
						player.inventory.setInventorySlotContents(
								player.inventory.currentItem, new ItemStack(
										Items.bucket));
					}else {
					player.inventory.addItemStackToInventory(new ItemStack(
							Items.bucket));
					}
				}
				return true;
			} else if (item.getItem() == ChinaCraft.woodenBucket_Water) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2);
				if (!player.capabilities.isCreativeMode) {
					if (--item.stackSize <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(
								ChinaCraft.woodenBucket));
					}else {
					player.inventory.addItemStackToInventory(new ItemStack(
							ChinaCraft.woodenBucket));
					}
				}
				return true;
			} else {
				return true;
			}
		}
	}
}
