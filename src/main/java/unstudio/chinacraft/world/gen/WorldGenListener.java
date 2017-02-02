package unstudio.chinacraft.world.gen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by Mouse on 2017/2/1.
 */
public class WorldGenListener{

    private final WorldGenCCOre WORLD_GEN_COPPER_ORE = new WorldGenCCOre(new int[]{0},20,64,0,8, ChinaCraft.copperOre);
    private final WorldGenCCOre WORLD_GEN_TIN_ORE = new WorldGenCCOre(new int[]{0},10,64,0,8, ChinaCraft.tinOre);
    private final WorldGenCCOre WORLD_GEN_SILVER_ORE = new WorldGenCCOre(new int[]{0},4,32,0,8, ChinaCraft.silverOre);
    private final WorldGenCCOre WORLD_GEN_JADE_ORE = new WorldGenCCOre(new int[]{0},4,64,32,4, ChinaCraft.jadeOre);
    //private final WorldGenCCOre WORLD_GEN_MARBLE_ORE = new WorldGenCCOre(new int[]{0},4,32,0,8, ChinaCraft.blockMarble);

    public WorldGenListener()
    {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event){
        WORLD_GEN_COPPER_ORE.generate(event.world,event.rand,event.worldX,0,event.worldZ);
        WORLD_GEN_TIN_ORE.generate(event.world,event.rand,event.worldX,0,event.worldZ);
        WORLD_GEN_SILVER_ORE.generate(event.world,event.rand,event.worldX,0,event.worldZ);
        WORLD_GEN_JADE_ORE.generate(event.world,event.rand,event.worldX,0,event.worldZ);
        //WORLD_GEN_MARBLE_ORE.generate(event.world,event.rand,event.worldX,0,event.worldZ);
    }
}
