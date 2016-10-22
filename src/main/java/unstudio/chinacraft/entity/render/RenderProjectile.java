package unstudio.chinacraft.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.entity.projectile.EntityProjectile;

@SideOnly(Side.CLIENT)
public class RenderProjectile
        extends Render {
    public boolean renderWithColor = true;
    private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private RenderBlocks itemRenderBlocks = new RenderBlocks();

    public void doRenderProjectile(EntityProjectile par1EntityProjectile, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glEnable(32826);
        float f = par1EntityProjectile.getDataWatcher().getWatchableObjectInt(23) / 10.0F;
        ItemStack item = par1EntityProjectile.getItemDisplay();
        GL11.glScalef(f, f, f);
        Tessellator tessellator = Tessellator.instance;
        if (par1EntityProjectile.isArrow()) {
            bindEntityTexture(par1EntityProjectile);
            GL11.glRotatef(par1EntityProjectile.prevRotationYaw + (par1EntityProjectile.rotationYaw - par1EntityProjectile.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(par1EntityProjectile.prevRotationPitch + (par1EntityProjectile.rotationPitch - par1EntityProjectile.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
            byte b0 = 0;
            float f2 = 0.0F;
            float f3 = 0.5F;
            float f4 = (0 + b0 * 10) / 32.0F;
            float f5 = (5 + b0 * 10) / 32.0F;
            float f6 = 0.0F;
            float f7 = 0.15625F;
            float f8 = (5 + b0 * 10) / 32.0F;
            float f9 = (10 + b0 * 10) / 32.0F;
            float f10 = 0.05625F;
            GL11.glEnable(32826);
            float f11 = par1EntityProjectile.arrowShake - par9;
            if (f11 > 0.0F) {
                float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
                GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
            }
            GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(f10, f10, f10);
            GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
            GL11.glNormal3f(f10, 0.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f8);
            tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f8);
            tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f9);
            tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f9);
            tessellator.draw();
            GL11.glNormal3f(-f10, 0.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f8);
            tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f8);
            tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f9);
            tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f9);
            tessellator.draw();
            for (int i = 0; i < 4; i++) {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glNormal3f(0.0F, 0.0F, f10);
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, f2, f4);
                tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, f3, f4);
                tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, f3, f5);
                tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, f2, f5);
                tessellator.draw();
            }
        } else if (par1EntityProjectile.is3D()) {
            GL11.glRotatef(par1EntityProjectile.prevRotationYaw + (par1EntityProjectile.rotationYaw - par1EntityProjectile.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(par1EntityProjectile.prevRotationPitch + (par1EntityProjectile.rotationPitch - par1EntityProjectile.prevRotationPitch) * par9 - 180.0F, 0.0F, 0.0F, 1.0F);
            if ((item.getItemSpriteNumber() == 0) && ((item.getItem() instanceof ItemBlock)) && (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item.getItem()).getRenderType()))) {
                Block block = Block.getBlockFromItem(item.getItem());
                bindTexture(TextureMap.locationBlocksTexture);
                this.itemRenderBlocks.renderBlockAsItem(block, item.getItemDamage(), 1.0F);
            } else {
                GL11.glTranslatef(-0.6F, -0.6F, 0.0F);
                if (item.getItem().requiresMultipleRenderPasses()) {
                    for (int k = 0; k < item.getItem().getRenderPasses(item.getItemDamage()); k++) {
                        float f8 = 1.0F;
                        if (this.renderWithColor) {
                            int i = item.getItem().getColorFromItemStack(item, k);
                            float f5 = (i >> 16 & 0xFF) / 255.0F;
                            float f4 = (i >> 8 & 0xFF) / 255.0F;
                            float f6 = (i & 0xFF) / 255.0F;
                            GL11.glColor4f(f5 * f8, f4 * f8, f6 * f8, 1.0F);
                            this.renderManager.itemRenderer.renderItem(Minecraft.getMinecraft().thePlayer, item, 0);
                        } else {
                            this.renderManager.itemRenderer.renderItem(Minecraft.getMinecraft().thePlayer, item, 0);
                        }
                    }
                } else {
                    IIcon icon1 = item.getIconIndex();
                    if (this.renderWithColor) {
                        int l = item.getItem().getColorFromItemStack(item, 0);
                        float f8 = (l >> 16 & 0xFF) / 255.0F;
                        float f9 = (l >> 8 & 0xFF) / 255.0F;
                        float f5 = (l & 0xFF) / 255.0F;
                        float f4 = 1.0F;
                        renderDroppedItem(item, icon1, par9, f8 * f4, f9 * f4, f5 * f4, f);
                    } else {
                        renderDroppedItem(item, icon1, par9, 1.0F, 1.0F, 1.0F, f);
                    }
                }
            }
        } else {
            IIcon icon = item.getItem().getIconFromDamage(item.getItemDamage());

            bindTexture(TextureMap.locationItemsTexture);
            if (item.getItem().requiresMultipleRenderPasses()) {
                for (int k = 0; k < item.getItem().getRenderPasses(item.getItemDamage()); k++) {
                    int i = item.getItem().getColorFromItemStack(item, k);
                    float f5 = (i >> 16 & 0xFF) / 255.0F;
                    float f4 = (i >> 8 & 0xFF) / 255.0F;
                    float f6 = (i & 0xFF) / 255.0F;
                    GL11.glColor4f(f5, f4, f6, 1.0F);
                }
            }
            if ((icon == ItemPotion.func_94589_d("bottle_splash")) || (icon == ItemPotion.func_94589_d("bottle_drinkable"))) {
                int var12 = PotionHelper.func_77915_a(item.getItemDamage(), false);
                float var13 = (var12 >> 16 & 0xFF) / 255.0F;
                float var14 = (var12 >> 8 & 0xFF) / 255.0F;
                float var15 = (var12 & 0xFF) / 255.0F;
                GL11.glColor3f(var13, var14, var15);
                GL11.glPushMatrix();
                renderSprite(tessellator, ItemPotion.func_94589_d("overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
            renderSprite(tessellator, icon);
        }
        if ((par1EntityProjectile.is3D()) && (par1EntityProjectile.glows())) {
            GL11.glDisable(2896);
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
        GL11.glEnable(2896);
    }

    private void renderSprite(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
        par1Tessellator.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
        par1Tessellator.draw();
    }

    private void renderDroppedItem(ItemStack item, IIcon par2Icon, float par4, float par5, float par6, float par7, float par8) {
        Tessellator tessellator = Tessellator.instance;
        if (par2Icon == null) {
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            ResourceLocation resourcelocation = texturemanager.getResourceLocation(item.getItemSpriteNumber());
            par2Icon = ((TextureMap) texturemanager.getTexture(resourcelocation)).registerIcon("missingno");
        }
        float f4 = par2Icon.getMinU();
        float f5 = par2Icon.getMaxU();
        float f6 = par2Icon.getMinV();
        float f7 = par2Icon.getMaxV();

        float f12 = 0.0625F;
        if (item.getItemSpriteNumber() == 0) {
            bindTexture(TextureMap.locationBlocksTexture);
        } else {
            bindTexture(TextureMap.locationItemsTexture);
        }
        GL11.glColor4f(par5, par6, par7, 1.0F);
        ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, par2Icon.getIconWidth(), par2Icon.getIconHeight(), f12);
        if ((item != null) && (item.hasEffect(0))) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            this.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
            GL11.glEnable(3042);
            GL11.glBlendFunc(768, 1);
            float f13 = 0.76F;
            GL11.glColor4f(0.5F * f13, 0.25F * f13, 0.8F * f13, 1.0F);
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            GL11.glScalef(par8, par8, par8);
            float f15 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(f15, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(par8, par8, par8);
            f15 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-f15, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
        }
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        doRenderProjectile((EntityProjectile) par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110779_a(EntityProjectile par1EntityProjectile) {
        return par1EntityProjectile.isArrow() ? arrowTextures : this.renderManager.renderEngine.getResourceLocation(par1EntityProjectile.getItemDisplay().getItemSpriteNumber());
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110779_a((EntityProjectile) par1Entity);
    }
}
