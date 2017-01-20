package unstudio.chinacraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.block.model.BlockCCModel;
import unstudio.chinacraft.client.model.block.ModelDing;
import unstudio.chinacraft.client.render.item.ModelBlockItemRenderer;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

/**
 * Created by trychen on 16/10/29.
 */
public class BlockCCDing extends BlockCCModel {
    public static final int[][] field_149981_a = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public BlockCCDing() {
        super(Material.rock, ModelDing.class,"ding",null);
        setHardness(5f);
        setCreativeTab(null);
    }

    @Override
    public boolean canPlaceBlockOnSide(World w, int x, int y, int z, int a) {
        return super.canPlaceBlockOnSide(w, x, y, z, a);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.mossy_cobblestone.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return false;
    }

    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
        int i1 = getDirection(l);

        if (isBlockHeadOfDing(l)) {
            if (p_149695_1_.getBlock(p_149695_2_ - field_149981_a[i1][0], p_149695_3_, p_149695_4_ - field_149981_a[i1][1]) != this) {
                p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
            }
        } else if (p_149695_1_.getBlock(p_149695_2_ + field_149981_a[i1][0], p_149695_3_, p_149695_4_ + field_149981_a[i1][1]) != this) {
            p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);

            if (!p_149695_1_.isRemote) {
                this.dropBlockAsItem(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0);
            }
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        this.setBlockBounds(0.0F, 0.0F, 0.0f, 1F, 1.35f, 1F);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return isBlockHeadOfDing(p_149650_1_) ? Item.getItemById(0) : ChinaCraft.itemDing;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(ChinaCraft.itemDing);
    }

    @Override
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ChinaCraft.itemDing;
    }

    public boolean isBlockHeadOfDing(int metaDate) {
        return (metaDate & 8) != 0;
    }

    public void onBlockHarvested(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
        if (p_149681_6_.capabilities.isCreativeMode && isBlockHeadOfDing(p_149681_5_)) {
            int i1 = getDirection(p_149681_5_);
            p_149681_2_ -= field_149981_a[i1][0];
            p_149681_4_ -= field_149981_a[i1][1];

            if (p_149681_1_.getBlock(p_149681_2_, p_149681_3_, p_149681_4_) == this) {
                p_149681_1_.setBlockToAir(p_149681_2_, p_149681_3_, p_149681_4_);
            }
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        if (!isBlockHeadOfDing(p_149690_5_)) {
            super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0);
        }
    }

    public static class ItemCustomRender implements ModelBlockItemRenderer.Custom {
        @Override
        public void render(IItemRenderer.ItemRenderType type) {
            if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                GL11.glScalef(0.65f, 0.57f, 0.65f);
                GL11.glTranslatef(-0.5f, 1.2f, 0);
                GL11.glRotatef(90F, 0F, 1F, 0F);
            }
        }
    }

    public static int getDirection(int p_149895_0_)
    {
        return p_149895_0_ & 3;
    }
}
