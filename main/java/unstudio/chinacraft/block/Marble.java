package unstudio.chinacraft.block;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMinable;
import unstudio.chinacraft.ChinaCraft;

public class Marble extends Block implements IWorldGenerator{
	public Marble() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("marble"));
		setHardness(3.5F);
		setResistance(20.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == 0&&random.nextInt(3)==0) {
				int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
				int firstBlockYCoord = random.nextInt(32)+28;
				int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);

				(new WorldGenBlockBlob(this, 2)).generate(
						world, random, firstBlockXCoord, firstBlockYCoord,
						firstBlockZCoord);
		}
	}
}
