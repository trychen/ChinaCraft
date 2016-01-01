package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBuckpot extends Block {
	/**
	 * 陶锅
	 **/
	public BlockBuckpot() {
		super(Material.rock);
		setBlockName("buckpot");
		setHardness(0.5F);
		setResistance(5.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
