package unstudio.chinacraft.common;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import unstudio.chinacraft.entity.projectile.EntityThrownBomb;
import unstudio.chinacraft.entity.projectile.EntityThrownFirecracker;
import unstudio.chinacraft.event.ListenerRegister;
import unstudio.chinacraft.tileentity.*;
import unstudio.chinacraft.client.waila.WailaCompatibility;
import unstudio.chinacraft.common.config.*;
import unstudio.chinacraft.util.annotation.ItemBlockRegister;
import unstudio.chinacraft.world.gen.WorldGenCCFlower;
import unstudio.chinacraft.world.gen.WorldGenListener;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        //配置文件载入
        new ConfigLoader(new Configuration(event.getSuggestedConfigurationFile()));

        // 由于ChinaCraft主类中ItemSilkworm实例生成的比Config实例要早，因此这些只能被迫在这儿进行
        if (FeatureConfig.enableAdvancedSericulture) {
            ChinaCraft.silkworm.setMaxDamage(99);
            ChinaCraft.silkworm.setNoRepair();
        }

        // 初始化Waila支持
        if (ChinaCraft.WAILAIsLoad)
            WailaCompatibility.init();

        //套装载入 TODO:这是一个旧的载入方法
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

        //注册生成器
        new WorldGenListener();
        GameRegistry.registerWorldGenerator(new WorldGenMulberryTree(true), 1);

        //注册TileEntity
        GameRegistry.registerTileEntity(TileJadeBench.class, "tileEntityCCJadeWorkingTable"); // 玉石工作台TileEntity
        GameRegistry.registerTileEntity(TileDrum.class, "tileEntityCCDrum");
        GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityCCBuhrimill"); // 石磨TileEntity
        GameRegistry.registerTileEntity(TileCookingBench.class, "tileEntityCCCookingBench");
        GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame"); // 养蚕架TileEntity
        GameRegistry.registerTileEntity(TilePotteryTable.class, "tileEntityCCPotteryTable");
        GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileCCFirebrickStructure");
        GameRegistry.registerTileEntity(TilePotteryKiln.class, "tileCCPotteryKiln");
        GameRegistry.registerTileEntity(TileModelBlock.class, "tileEntityCCModelBlock");

        //GameRegistry.registerBlock(ChinaCraft.sericultureFrame, ItemCCBlock.class, "SericultureFrame"); // 养蚕架

        GameRegistry.registerItem(ChinaCraft.nightClothes[0], "NightClothesHead");
        GameRegistry.registerItem(ChinaCraft.nightClothes[1], "NightClothesBody");
        GameRegistry.registerItem(ChinaCraft.nightClothes[2], "NightClothesLeg");
        GameRegistry.registerItem(ChinaCraft.nightClothes[3], "NightClothesShoe");

        Recipes.init(); //载入配方

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

        try {
            Field seedListField = ForgeHooks.class.getDeclaredField("seedList");
            seedListField.setAccessible(true);
            List seedList = (List) seedListField.get(null);
            Constructor constructor= seedList.get(0).getClass().getConstructor(ItemStack.class, int.class);
            constructor.setAccessible(true);
            seedList.add(constructor.newInstance(new ItemStack(ChinaCraft.rices), 1));
            seedList.add(constructor.newInstance(new ItemStack(ChinaCraft.soy), 1));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        initBlockFlammbility();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    private void initBlockFlammbility(){
        Blocks.fire.setFireInfo(ChinaCraft.bambooPlank,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.bambooSlab,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.bambooDoubleSlab,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.bambooStair,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.bamboo,5,5);
        Blocks.fire.setFireInfo(ChinaCraft.blockBambooShoot,5,10);
        Blocks.fire.setFireInfo(ChinaCraft.bambooFence,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.bambooFenceGate,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.mulberryLog,5,5);
        Blocks.fire.setFireInfo(ChinaCraft.mulberryWood,5,20);
        Blocks.fire.setFireInfo(ChinaCraft.mulberryLeaf,30,60);
        Blocks.fire.setFireInfo(ChinaCraft.mulberrySapling,10,30);
    }
}
