package unstudio.sinocraft.client.render.item;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;


public class HugeItemRenderer implements IItemRenderer {

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public static TextureManager texturemanager;

    public HugeItemRenderer() {
        texturemanager = FMLClientHandler.instance().getClient().getTextureManager();
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return (type == ItemRenderType.ENTITY) || (type == ItemRenderType.EQUIPPED) || (type == ItemRenderType.EQUIPPED_FIRST_PERSON);
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return (type == ItemRenderType.ENTITY) && ((helper == ItemRendererHelper.ENTITY_ROTATION) || (helper == ItemRendererHelper.ENTITY_BOBBING));
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object[] data) {
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glScalef(2f, 2f, 2f);
            GL11.glTranslatef(0f,-0.14f,0.25f);
            IIcon iicon = ((EntityLivingBase) data[1]).getItemIcon(item, 0);
            renderGiantItemEquipped(iicon, item);
        } else if (type == ItemRenderType.EQUIPPED) {
            float scale = 1.9F;
            GL11.glScalef(scale, scale, scale);

            GL11.glTranslatef(-0.45F, -0.02F, 0.03F);
            IIcon iicon = ((EntityLivingBase) data[1]).getItemIcon(item, 0);
            renderGiantItemEquipped(iicon, item);
        } else if (type == ItemRenderType.ENTITY) {
            GL11.glScalef(3f, 3f, 3f);

            GL11.glTranslatef(0.0F, 0.15F, 0.0F);

            EntityItem entityItem = (EntityItem) data[1];

            renderDroppedItem(entityItem, item);
        }
    }

    public static void renderGiantItemEquipped(IIcon iicon, ItemStack par2ItemStack) {
        int par3 = 0;

        if (iicon == null) {
            GL11.glPopMatrix();
            return;
        }

        texturemanager.getTexture(texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
        TextureUtil.func_152777_a(false, false, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        float f = iicon.getMinU();
        float f1 = iicon.getMaxU();
        float f2 = iicon.getMinV();
        float f3 = iicon.getMaxV();

        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

        if (par2ItemStack.hasEffect(par3)) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            texturemanager.getTexture(RES_ITEM_GLINT);
            GL11.glEnable(3042);
            OpenGlHelper.func_153186_a(768, 1, 1, 0);
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

        texturemanager.getTexture(texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
        TextureUtil.func_147945_b();
    }

    static void renderDroppedItem(EntityItem entityItem, ItemStack item) {
        Tessellator tessellator = Tessellator.instance;

        float f9 = 0.5F;
        float f10 = 0.25F;

        GL11.glPushMatrix();

        float f12 = 0.0625F;
        float f11 = 0.021875F;

        GL11.glTranslatef(-f9, -f10, -(f12 + f11));

        GL11.glTranslatef(0.0F, 0.0F, f12 + f11);

        texturemanager.getTexture(TextureMap.locationItemsTexture);

        IIcon par2Icon = item.getIconIndex();

        ItemRenderer.renderItemIn2D(tessellator, par2Icon.getMaxU(), par2Icon.getMinV(), par2Icon.getMinU(), par2Icon.getMaxV(), par2Icon.getIconWidth(), par2Icon.getIconHeight(), f12);

        GL11.glPopMatrix();
    }
}
