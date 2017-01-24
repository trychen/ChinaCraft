package unstudio.chinacraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.client.model.block.ModelBuhrimill;
import unstudio.chinacraft.tileentity.TileBuhrimill;

public class TileEntityBuhrimillRenderer extends TileEntitySpecialRenderer {
    public static final ModelBuhrimill model = new ModelBuhrimill();
    public static final ResourceLocation textures = new ResourceLocation("chinacraft:textures/models/block/buhrimill.png");

    public TileEntityBuhrimillRenderer() {}

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        ModelBuhrimill modelBuhrimill = this.model;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        this.bindTexture(textures);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
        modelBuhrimill.i1.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i2.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i3.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i4.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i5.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i6.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i7.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.i8.rotateAngleY = ((TileBuhrimill) te).angle / 180F * (float) Math.PI;
        modelBuhrimill.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
