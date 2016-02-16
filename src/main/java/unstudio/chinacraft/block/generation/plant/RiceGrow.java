package unstudio.chinacraft.block.generation.plant;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RiceGrow extends BlockCrops {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public RiceGrow() {
        super();
    }

    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par2 >= 5) {
            if (par2 < 7) {
                return this.icons[3];
            } else {
                return this.icons[4];
            }
        } else {
            return this.icons[par2 >> 1];
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if (meta != 5 && meta != 6) {
            return 1;
        } else {
            int ret = 2;
            for (int n = 0; n < 3 + fortune; n++) {
                if (random.nextInt(15) <= meta) {
                    ret++;
                }
            }
            return ret;
        }
    }

    @Override
    protected Item func_149866_i() {
        return ChinaCraft.lcker;
    }

    @Override
    protected Item func_149865_P() {
        return ChinaCraft.lcker;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return par1 >= 5 ? ChinaCraft.lcker : null;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[5];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = par1IconRegister.registerIcon("chinacraft:rice_stage_" + i);
        }
    }
}
