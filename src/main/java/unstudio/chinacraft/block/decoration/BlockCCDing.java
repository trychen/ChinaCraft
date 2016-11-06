package unstudio.chinacraft.block.decoration;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.block.model.BlockCCModel;
import unstudio.chinacraft.client.model.block.ModelDing;
import unstudio.chinacraft.client.render.item.ModelBlockItemRenderer;

import java.util.List;

/**
 * Created by trychen on 16/10/29.
 */
public class BlockCCDing extends BlockCCModel {
    public BlockCCDing() {
        super(Material.rock, ModelDing.class, "ding", null);
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_) {
        return super.canPlaceBlockOnSide(p_149707_1_,p_149707_2_,p_149707_3_,p_149707_4_,p_149707_5_);
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        return super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
    }

    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
                                EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        if (l == 0) p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2);
        if (l == 1) p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 1, 2);
        if (l == 2) p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        if (l == 3) p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.mossy_cobblestone.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        int l = p_149719_1_.getTileEntity(p_149719_2_,p_149719_3_,p_149719_4_).getBlockMetadata();

//        switch (l){
//            case 0:
//                this.setBlockBounds(0.0F, 0.0F, 0.25F, 1F, 1.35f, 1.75F);
//                break;
//            case 1:
//                this.setBlockBounds(-0.75F, 0.0F, 0.0F, 0.75F, 1.35f, 1.0F);
//                break;
//            case 2:
//                this.setBlockBounds(0.0F, 0.0F, -0.75F, 1F, 1.35f, 0.75F);
//                break;
//            case 3:
//                this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.75F, 1.35f, 1.0F);
//                break;
//        }

        this.setBlockBounds(l==1?-0.75f:l==3?0.25f:0, 0.0F, l==0?0.25f:l==2?-0.75f:0, l==1?0.75f:l==4?01.75f:1f, 1.35f, l==0?1.75f:l==2?0.75f:1.0f);
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
}
