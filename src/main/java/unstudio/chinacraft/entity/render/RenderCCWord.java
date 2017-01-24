package unstudio.chinacraft.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import unstudio.chinacraft.client.model.block.ModelBuhrimill;
import unstudio.chinacraft.client.model.entity.ModelWord;

/**
 * Created by trychen on 17/1/19.
 */
@SideOnly(Side.CLIENT)
public class RenderCCWord extends Render {
    public static final ModelWord model = new ModelWord();
    public static final ResourceLocation textures = new ResourceLocation("chinacraft:textures/blocks/fu.png");

    @Override
    public void doRender(Entity a, double x, double y, double z, float p_147500_8_, float p_76986_9_) {
        EntityHanging p_76986_1_ = (EntityHanging) a;
        GL11.glPushMatrix();
        System.out.println(p_76986_1_.field_146064_c);
        GL11.glTranslated(x,y - 0.5, z);
        GL11.glRotatef(p_147500_8_, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.bindTexture(textures);
//        float f2 = 0.0625F;
//        GL11.glScalef(f2, f2, f2);
        model.render(null,0,0,0,0,0,0.0625f);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();

//        GL11.glPushMatrix();
//        double d3 = p_76986_1_.posX - x - 0.5D;
//        double d4 = p_76986_1_.posY - y - 0.5D;
//        double d5 = p_76986_1_.posZ - z - 0.5D;
//        int i = p_76986_1_.field_146063_b + Direction.offsetX[p_76986_1_.hangingDirection];
//        int j = p_76986_1_.field_146064_c;
//        int k = p_76986_1_.field_146062_d + Direction.offsetZ[p_76986_1_.hangingDirection];
//        GL11.glTranslated((double)i - d3, (double)j - d4, (double)k - d5);
//        this.bindTexture(textures);
//        model.render(null,0,0,0,0,0,0.0625f);
//        GL11.glPopMatrix();
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
