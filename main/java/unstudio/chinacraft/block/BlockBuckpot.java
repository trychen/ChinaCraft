package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBuckpot extends Block {

	public BlockBuckpot() {
		super(Material.rock);
		setBlockName("buckpot");
		setHardness(0.5F);
		setResistance(5.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_,int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_,int p_149727_6_, float p_149727_7_, float p_149727_8_,float p_149727_9_) {
		
		return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_,p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_,p_149727_9_);
	}
}
