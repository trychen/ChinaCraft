package unstudio.chinacraft.block.decoration;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class MarbleSlab extends BlockSlab{

	public MarbleSlab(boolean b) {
		super(b,Material.rock);
		setHardness(2.0F);
        setResistance(10.0F);
        setStepSound(soundTypePiston);
        setBlockName(StatCollector.translateToLocal("marble_slab"));
		setLightOpacity(0);
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
	       return null;
	}

    @Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(ChinaCraft.marbleSlab);
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ChinaCraft.marbleSlab);
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entity, int l, float m, float n, float o)
    {
    	if (world.getBlock(x, y, z) == ChinaCraft.marbleDoubleSlab) {
    		super.onBlockActivated(world, x, y, z, entity, l, m, n, o);
    		return false;
    	}
    	ItemStack itemstack = entity.inventory.getCurrentItem();
    	if (itemstack != null&&itemstack.getItem() == Item.getItemFromBlock(ChinaCraft.marbleSlab)) {
    		if(world.getBlock(x, y, z).getBlockBoundsMinY() == 0.5D&&l!=0) {
    			super.onBlockActivated(world, x, y, z, entity, l, m, n, o);
    			return false;
    		}
    		if(world.getBlock(x, y, z).getBlockBoundsMinY() == 0.0D&&l!=1) {
    			super.onBlockActivated(world, x, y, z, entity, l, m, n, o);
    			return false;
    		}
            if (!entity.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;

                if (itemstack.stackSize <= 0)
                {
                    entity.inventory.setInventorySlotContents(entity.inventory.currentItem, (ItemStack)null);
                }
            }
    		world.setBlockToAir(x, y, z);
    		world.setBlock(x, y, z, ChinaCraft.marbleDoubleSlab, 0, 2);
    	}
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    private static boolean func_150003_a(Block p_150003_0_)
    {
        return false;
    }

}
