package unstudio.chinacraft.block;

import java.util.Random;

import unstudio.chinacraft.ChinaCraft;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class JadeOre extends Block implements IWorldGenerator {

	public JadeOre() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("jade_ore"));
		setHardness(3.0F);
		setResistance(15.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == 0) {
			for (int i = 0; i < 4; i++) {
				int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
				int firstBlockYCoord = random.nextInt(32) + 32;
				int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);

				(new WorldGenMinable(this, 1, 4, Blocks.stone)).generate(world,
						random, firstBlockXCoord, firstBlockYCoord,
						firstBlockZCoord);
			}
		}
	}
}
