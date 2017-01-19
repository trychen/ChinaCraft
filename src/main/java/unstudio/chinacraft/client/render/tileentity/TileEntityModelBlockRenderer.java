package unstudio.chinacraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.block.model.BlockCCModel;
import unstudio.chinacraft.tileentity.TileModelBlock;

public class TileEntityModelBlockRenderer extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        BlockCCModel block = (BlockCCModel)te.blockType;
        ModelBase model = block.getModel();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(block.getTexture());

        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
        if (model instanceof ModelRenderer){
            ((ModelRenderer)model).render(te,x,y,z);
        } else {
            model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public interface ModelRenderer{
        void render(TileEntity tile, double x, double y, double z);
    }
}
