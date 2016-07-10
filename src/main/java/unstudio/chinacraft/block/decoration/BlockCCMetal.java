package unstudio.chinacraft.block.decoration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.Recipes;

public class BlockCCMetal extends BlockBase implements Recipes.RecipeAble{
    private Item recipesIteml;

    /**
     * 金属成品的合成方块
     * @param name 名字
     * @param harvestLevel 允许的工具等级
     * @param hardness 硬度
     */
    public BlockCCMetal(String name, int harvestLevel, float hardness) {
        super(Material.rock);
//        setBlockName(StatCollector.translateToLocal("bronze_block"));
        setBlockName(name);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(ChinaCraft.tabCore);
        setHardness(hardness);
        setHarvestLevel("pickaxe", harvestLevel);
    }

    /**
     * 设置用于合成的物品
     * @param item 用于合成的物品
     * @return
     */
    public BlockCCMetal setRecipesItem(Item item){
        recipesIteml=item;
        return this;
    }

    public static void recipes() {

    }
}
