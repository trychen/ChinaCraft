package unstudio.chinacraft.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFirebrickStructure extends BlockContainer {
	
	private IIcon potterykiln_off,potterykiln_on,firebrick;

	public BlockFirebrickStructure() {
		super(Material.rock);
		setBlockName("firebrick");
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileFirebrickStructure();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(world.isRemote) return true;
		TileFirebrickStructure tile = (TileFirebrickStructure) world.getTileEntity(x, y, z);
		p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_PotteryKiln, world, tile.getX(), tile.getY(), tile.getZ());
		return true;
    }
	
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_,int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,p_149749_5_, p_149749_6_);
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,int p_149650_3_) {
		return Item.getItemFromBlock(ChinaCraft.blockFirebrick);
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_,int p_149694_4_) {
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
		if(p_149691_2_ == 0) return firebrick;
		else if(p_149691_2_ == 1) return potterykiln_off;
		else if(p_149691_2_ == 2) return potterykiln_on;
		else return firebrick;
	}
}
