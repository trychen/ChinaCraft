package unstudio.chinacraft.entity.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntitySweepAttackFX extends EntityFX
{
    private static final ResourceLocation SWEEP_TEXTURE = new ResourceLocation("chinacraft:textures/entity/sweep.png");
    private int life;
    private int lifeTime;
    /** The Rendering Engine. */
    private TextureManager theRenderEngine;
    private float size;

    public EntitySweepAttackFX(TextureManager p_i1213_1_, World p_i1213_2_, double p_i1213_3_, double p_i1213_5_, double p_i1213_7_, double p_i1213_9_, double p_i1213_11_, double p_i1213_13_)
    {
        super(p_i1213_2_, p_i1213_3_, p_i1213_5_, p_i1213_7_, 0, 0, 0);
        this.theRenderEngine = p_i1213_1_;
        this.lifeTime = 4;
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.size = 1.0F - (float)p_i1213_9_ * 0.5F;

    }

    public void renderParticle(Tessellator p_70539_1_, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        int i = (int)(((float)this.life + partialTicks) * 3.0F / (float)this.lifeTime);

        if (i <= 7)
        {
            this.theRenderEngine.bindTexture(SWEEP_TEXTURE);
            float f = (float)(i % 4) / 4.0F;
            float f1 = f + 0.24975F;
            float f2 = (float)(i / 2) / 2.0F;
            float f3 = f2 + 0.4995F;
            float f4 = 1.0F * this.size;
            float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
            float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
            float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            RenderHelper.disableStandardItemLighting();
            p_70539_1_.startDrawingQuads();
            p_70539_1_.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
            p_70539_1_.setBrightness(240);

//                  worldRendererIn.pos((double)(f5 - rotationX * f4 - rotationXY * f4), (double)(f - rotationZ * f4 * 0.5F), (double)(f1 - rotationYZ * f4 - rotationXZ * f4)).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
//                  worldRendererIn.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double)(f + rotationZ * f4 * 0.5F), (double)(f1 - rotationYZ * f4 + rotationXZ * f4)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
//                  worldRendererIn.pos((double)(f5 + rotationX * f4 + rotationXY * f4), (double)(f + rotationZ * f4 * 0.5F), (double)(f1 + rotationYZ * f4 + rotationXZ * f4)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
//                  worldRendererIn.pos((double)(f5 + rotationX * f4 - rotationXY * f4), (double)(f - rotationZ * f4 * 0.5F), (double)(f1 + rotationYZ * f4 - rotationXZ * f4)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            p_70539_1_.addVertexWithUV((double)(f5 - rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4 * 0.5f), (double)(f7 - rotationYZ * f4 - rotationXZ * f4), (double)f1, (double)f3);
            p_70539_1_.addVertexWithUV((double)(f5 - rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4 * 0.5f), (double)(f7 - rotationYZ * f4 + rotationXZ * f4), (double)f1, (double)f2);
            p_70539_1_.addVertexWithUV((double)(f5 + rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4 * 0.5f), (double)(f7 + rotationYZ * f4 + rotationXZ * f4), (double)f, (double)f2);
            p_70539_1_.addVertexWithUV((double)(f5 + rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4 * 0.5f), (double)(f7 + rotationYZ * f4 - rotationXZ * f4), (double)f, (double)f3);
            p_70539_1_.draw();
            GL11.glPolygonOffset(0.0F, 0.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    public int getBrightnessForRender(float p_70070_1_)
    {
        return 61680;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.life;

        if (this.life == this.lifeTime)
        {
            this.setDead();
        }
    }

    public int getFXLayer()
    {
        return 3;
    }
}