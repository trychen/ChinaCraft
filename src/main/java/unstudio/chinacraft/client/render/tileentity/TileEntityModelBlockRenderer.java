package unstudio.chinacraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.tileentity.TileModelBlock;

public class TileEntityModelBlockRenderer extends TileEntitySpecialRenderer {
    private boolean a = true;

    public TileEntityModelBlockRenderer(){
        System.out.println("Created Renderer");
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        TileModelBlock ti = (TileModelBlock) te;
        ModelBase model = ti.getModel();
        System.out.println(this.getClass() + "Start Rendered");

        if (a){
            System.out.println(ti.getModel() == null);
            System.out.println(ti.getTexture() == null);
            a = false;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(ti.getTexture());
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
