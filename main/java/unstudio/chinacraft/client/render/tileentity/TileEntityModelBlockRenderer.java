package unstudio.chinacraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import unstudio.chinacraft.tileentity.TileModelBlock;
import org.lwjgl.opengl.GL11;


public class TileEntityModelBlockRenderer extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        TileModelBlock ti = (TileModelBlock) te;
        ModelBase model = ti.getModel();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
        Minecraft.getMinecraft().
                renderEngine.bindTexture(new ResourceLocation("chinacraft:textures/models/block/"+ti.getTexture()+".png"));
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glScaled(0.5, 0.5, 0.5);
        GL11.glPushMatrix();
        GL11.glRotatef(te.getBlockMetadata() * 90 + 180, 0.0F, 1.0F, 0.0F);
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}