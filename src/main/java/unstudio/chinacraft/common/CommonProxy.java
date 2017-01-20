package unstudio.chinacraft.common;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import unstudio.chinacraft.entity.projectile.EntityThrownBomb;
import unstudio.chinacraft.entity.projectile.EntityThrownFirecracker;
import unstudio.chinacraft.event.ListenerRegister;
import unstudio.chinacraft.item.ItemCCBlock;
import unstudio.chinacraft.tileentity.*;
import unstudio.chinacraft.client.waila.WailaCompatibility;
import unstudio.chinacraft.common.config.*;
import unstudio.chinacraft.util.annotation.ItemBlockRegister;
import unstudio.chinacraft.world.gen.WorldGenCCFlower;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new ConfigLoader(new Configuration(event.getSuggestedConfigurationFile()));

        // 由于ChinaCraft主类中ItemSilkworm实例生成的比Config实例要早，因此这些只能被迫在这儿进行
        if (FeatureConfig.enableAdvancedSericulture) {
            ChinaCraft.silkworm.setMaxDamage(99);
            ChinaCraft.silkworm.setNoRepair();
        }

        // 初始化Waila支持
        if (ChinaCraft.WAILAIsLoad)
            WailaCompatibility.init();

        ChinaCraft.bronzeHelmet = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, ChinaCraft.bronzeArmorTexture,
                0).setUnlocalizedName("bronze_helmet").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);// 青铜头盔
        ChinaCraft.bronzeChestplate = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                ChinaCraft.bronzeArmorTexture, 1).setUnlocalizedName("bronze_body").setMaxStackSize(1)
                .setCreativeTab(ChinaCraft.tabTool);// 青铜胸甲
        ChinaCraft.bronzeLeggings = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                ChinaCraft.bronzeArmorTexture, 2).setUnlocalizedName("bronze_legs").setMaxStackSize(1)
                .setCreativeTab(ChinaCraft.tabTool);// 青铜护腿
        ChinaCraft.bronzeBoots = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, ChinaCraft.bronzeArmorTexture,
                3).setUnlocalizedName("bronze_boots").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);// 青铜靴子
    }

    public void init(FMLInitializationEvent event) {
        // 注册主类为物品集合
        ItemBlockRegister.registerAll();

        // 注册通讯
        NetworkRegistry.INSTANCE.registerGuiHandler(ChinaCraft.instance, new GuiHandler());

        // 初始化监听器
        ListenerRegister.init();

        GameRegistry.registerWorldGenerator(ChinaCraft.copperOre, 3);
        GameRegistry.registerWorldGenerator(ChinaCraft.tinOre, 3);
        GameRegistry.registerWorldGenerator(ChinaCraft.jadeOre, 3);
        GameRegistry.registerWorldGenerator(ChinaCraft.blockMarble, 127);
        GameRegistry.registerWorldGenerator(new WorldGenCCFlower(), 1);
        GameRegistry.registerWorldGenerator(new WorldGenMulberryTree(true), 1);
        GameRegistry.registerWorldGenerator(ChinaCraft.blockBambooShoot, 1);

        GameRegistry.registerTileEntity(TileJadeBench.class, "tileEntityCCJadeWorkingTable"); // 玉石工作台TileEntity
        GameRegistry.registerTileEntity(TileDrum.class, "tileEntityCCDrum");
        GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityCCBuhrimill"); // 石磨TileEntity
        GameRegistry.registerTileEntity(TileCookingBench.class, "tileEntityCCCookingBench");
        GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame"); // 养蚕架TileEntity
        GameRegistry.registerTileEntity(TilePotteryTable.class, "tileEntityCCPotteryTable");

        GameRegistry.registerBlock(ChinaCraft.sericultureFrame, ItemCCBlock.class, "SericultureFrame"); // 养蚕架

        GameRegistry.registerItem(ChinaCraft.nightClothes[0], "NightClothesHead");
        GameRegistry.registerItem(ChinaCraft.nightClothes[1], "NightClothesBody");
        GameRegistry.registerItem(ChinaCraft.nightClothes[2], "NightClothesLeg");
        GameRegistry.registerItem(ChinaCraft.nightClothes[3], "NightClothesShoe");
        GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileCCFirebrickStructure");
        GameRegistry.registerTileEntity(TilePotteryKiln.class, "tileCCPotteryKiln");
        GameRegistry.registerTileEntity(TileModelBlock.class, "tileEntityCCModelBlock");

        Recipes.init();

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(ChinaCraft.woodenBucket_Water),
                new ItemStack(ChinaCraft.woodenBucket));

        BlockDispenser.dispenseBehaviorRegistry.putObject(ChinaCraft.firecracker, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World p_82499_1_, IPosition p_82499_2_) {
                return new EntityThrownFirecracker(p_82499_1_, p_82499_2_.getX(), p_82499_2_.getY(), p_82499_2_.getZ());
            }
        });
        BlockDispenser.dispenseBehaviorRegistry.putObject(ChinaCraft.bomb, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World p_82499_1_, IPosition p_82499_2_) {
                return new EntityThrownBomb(p_82499_1_, p_82499_2_.getX(), p_82499_2_.getY(), p_82499_2_.getZ());
            }
        });
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
