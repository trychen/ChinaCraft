package unstudio.chinacraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.client.model.block.ModelSericultureFrame;

public class TileEntitySericultureFrameRenderer extends TileEntitySpecialRenderer {
    public final ModelSericultureFrame model = new ModelSericultureFrame();
    public final ResourceLocation textures = (new ResourceLocation(
            "chinacraft:textures/models/block/SericultureFrame.png"));

    public TileEntitySericultureFrameRenderer() {}

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int destroyStage) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
