package unstudio.chinacraft.client.render.item;

/**
 * Created by trychen on 16/7/30.
 */
public class SpecialItemRender /*implements IItemRenderer*/ {
    /*
    private static final ResourceLocation enchant = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return (type == ItemRenderType.EQUIPPED) || (type == ItemRenderType.EQUIPPED_FIRST_PERSON);
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack par2ItemStack, Object... data) {
        if (!(par2ItemStack.getItem() instanceof ISpecialEquippedRender)) return;
        ISpecialEquippedRender renderer = (ISpecialEquippedRender) par2ItemStack.getItem();
        EntityLivingBase par1EntityLiving = (EntityLivingBase) data[1];
        GL11.glTranslatef(0.9375F, 0.0625F, 0.0F);
        GL11.glRotatef(-315.0F, 0.0F, 0.0F, 1.0F);

        switch (renderer.getSpecialRenderType()){
            case huge:
                GL11.glScalef(1.0F, 1.2F, 1.0F);
                GL11.glTranslatef(-0.12F, 0.14F, -0.16F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                break;
            case spear:
                GL11.glScalef(1.0F, 1.3F, 1.0F);
                GL11.glTranslatef(-0.12F, -0.24F, -0.16F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                break;
            case shield:
                GL11.glScalef(0.8F, 0.8F, 0.8F);
                if (FMLClientHandler.instance().getClient().gameSettings.keyBindUseItem.getIsKeyPressed())
                    GL11.glTranslatef(0.6F, 1.0F, -0.2F);
                else
                    GL11.glTranslatef(0.4F, 1.0F, -0.1F);
                GL11.glRotatef(-6.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
                break;
            case glaive:
                GL11.glTranslatef(0.03F, -0.4F, 0.08F);
                break;
            case staff:
                GL11.glScalef(1.0F, 1.14F, 1.0F);
                GL11.glTranslatef(0.14F, -0.3F, 0.08F);
        }

        renderer.doRender();
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.09375F, 0.0625F, 0.0F);
        renderItem3d(par1EntityLiving, par2ItemStack);
    }

    public void renderItem3d(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager texturemanager = mc.getTextureManager();
        int par3 = 0;
        texturemanager.bindTexture(texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = par1EntityLiving.getItemIcon(par2ItemStack, par3);
        if (icon == null) {
            return;
        }
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        float f4 = 0.0F;
        float f5 = 0.3F;
        GL11.glEnable(32826);
        GL11.glTranslatef(-f4, -f5, 0.0F);
        float f6 = 1.5F;
        GL11.glScalef(f6, f6, f6);
        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconHeight(), icon.getIconHeight(), 0.0625F);
        if (par2ItemStack.hasEffect(par3)) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            texturemanager.bindTexture(enchant);
            GL11.glEnable(3042);
            GL11.glBlendFunc(768, 1);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
        }
        GL11.glDisable(32826);
    }

    public enum RenderType{
        huge,spear,glaive,shield,staff,custom
    }*/
}