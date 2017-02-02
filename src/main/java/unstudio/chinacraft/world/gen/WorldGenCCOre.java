package unstudio.chinacraft.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Mouse on 2017/2/1.
 */
public class WorldGenCCOre extends WorldGenerator {

    private final Set<Integer> dimensionID = new HashSet();
    private final Set<Integer> biomeID = new HashSet();
    private final int frequency;
    private final int highest,lowest;
    private final WorldGenMinable gen;

    public WorldGenCCOre(int dimensions[], int biomes[], int frequency, int highest, int lowest, int size, Block oreBlock){
        this(dimensions,biomes,frequency, highest,lowest,size,oreBlock,null);
    }

    public WorldGenCCOre(int dimensions[], int frequency, int highest, int lowest, int size, Block oreBlock){
        this(dimensions,frequency, highest,lowest,size,oreBlock,null);
    }

    public WorldGenCCOre(int dimensions[], int frequency, int highest, int lowest, int size, Block oreBlock, Block predicate){
        this(dimensions,new int[0],frequency, highest,lowest,size,oreBlock,predicate);
    }

    public WorldGenCCOre(int dimensions[], int biomes[], int frequency, int highest, int lowest, int size, Block oreBlock, Block predicate){
        for(int i:dimensions) dimensionID.add(i);
        for(int i:biomes) biomeID.add(i);
        this.frequency = frequency;
        this.highest = highest;
        this.lowest = lowest;
        if(predicate==null) gen = new WorldGenMinable(oreBlock, size);
        else gen = new WorldGenMinable(oreBlock, size, predicate);
    }

    @Override
    public boolean generate(World worldIn, Random rand, int x, int y, int z) {
        if (!dimensionID.contains(worldIn.provider.dimensionId)) return true;

        for (int i = 0; i < frequency; i++) {
            int tx = x + rand.nextInt(16);
            int ty = rand.nextInt(highest - lowest) + lowest;
            int tz = z + rand.nextInt(16);

            if(!biomeID.isEmpty() && !biomeID.contains(worldIn.getBiomeGenForCoords(tx,tz).biomeID)) return true;

            gen.generate(worldIn, rand, tx,ty,tz);
        }
        return true;
    }
}
