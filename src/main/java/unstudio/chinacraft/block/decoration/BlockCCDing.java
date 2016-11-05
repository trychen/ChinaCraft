package unstudio.chinacraft.block.decoration;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
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
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        int l = p_149743_1_.getTileEntity(p_149743_2_,p_149743_3_,p_149743_4_).getBlockMetadata();
        if (l == 1){
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.35f, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.35f, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }
        if (l == 3) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.35f, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
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
