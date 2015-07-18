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
		setHardness(4.0F);
		setResistance(30000.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == 0&&chunkX%2==0&&chunkZ%2==0) {
			for (int i = 0; i < 4; i++) {
				int firstBlockXCoord = chunkX * 32 + random.nextInt(32);
				int firstBlockYCoord = random.nextInt(32)+28;
				int firstBlockZCoord = chunkZ * 32 + random.nextInt(32);

				(new WorldGenBlockBlob(this, 2)).generate(
						world, random, firstBlockXCoord, firstBlockYCoord,
						firstBlockZCoord);
			}
		}
	}

}
