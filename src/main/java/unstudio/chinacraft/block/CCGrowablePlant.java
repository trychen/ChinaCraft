package unstudio.chinacraft.block;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 种植物的父类
 */
public class CCGrowablePlant extends BlockCrops {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    private String name;
    private int textureAmount;
    private Item baseItem;
    private Item dropItem;

    /**
     * 种植物的父类
     * 
     * @param name 名字
     * @param textureAmount 材质数量，支持5和7个，种植的不同阶段
     * @param baseItem 种下去的物品（未完成掉落）
     * @param dropItem 生成物（完成掉哦了）
     */
    public CCGrowablePlant(String name, int textureAmount, Item baseItem, Item dropItem) {
        super();
        setBlockName(name);
        this.name = name;
        this.textureAmount = textureAmount;
        this.baseItem = baseItem;
        this.dropItem = dropItem;
    }

    @Override
    public IIcon getIcon(int par1, int par2) {
        if (textureAmount == 7) {
            return icons[par2 < 0 || par2 >= 7 ? 6 : par2 == 0 ? 0 : par2 - 1];
        } else {
            if (par2 < 5) {
                return this.icons[par2 >> 1];
            } else {
                if (par2 < 7) {
                    return this.icons[3];
                } else {
                    return this.icons[4];
                }
            }
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if (meta == 5 || meta == 6) {
            int ret = 2;
            for (int n = 0; n < 3 + fortune; n++) {
                if (random.nextInt(15) <= meta) {
                    ret++;
                }
            }
            return ret;
        } else {
            return 1;
        }
    }

    @Override
    protected Item func_149866_i() {
        return dropItem;
    }

    @Override
    protected Item func_149865_P() {
        return baseItem;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return par1 == 7 ? dropItem : baseItem;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    /**
     * 注册物品的材质,"chinacraft:名字_stage_步骤"
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.textureAmount];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = par1IconRegister.registerIcon("chinacraft:" + this.name + "_stage_" + i);
        }
    }
}
