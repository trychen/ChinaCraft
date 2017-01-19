package unstudio.chinacraft.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by trychen on 17/1/19.
 */
@SideOnly(Side.CLIENT)
public class RenderCCWord extends Render {
    @Override
    public void doRender(Entity p_76986_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_, float p_76986_9_) {
        GL11.glPushMatrix();
        GL11.glVertex2f(-0.5f,-0.5f);
        GL11.glVertex2f(-0.5f,0.5f);
        GL11.glVertex2f(0.5f,0.5f);
        GL11.glVertex2f(0.5f,-0.5f);
        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param p_110775_1_
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
