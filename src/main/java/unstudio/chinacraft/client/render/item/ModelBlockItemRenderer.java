package unstudio.chinacraft.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.client.model.ModelExtendBlock;

/**
 * Created by trych on 2016/1/2.
 */
public class ModelBlockItemRenderer implements IItemRenderer {
    private ModelExtendBlock model;
    private ResourceLocation texture;
    private Custom renderer;

    public ModelBlockItemRenderer(ModelExtendBlock model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item,
                                         IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glRotatef(180F, 1F, 0F, 0F);

        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(1.2F, 1.2F, 1.2F);
            GL11.glTranslatef(0.0F, -1.05F, 0.00F);
            // GL11.glRotatef(-45F, 0F, 1F, 0F);
//            GL11.glRotatef(0F, 0F, 0F, 1F);
            if (renderer != null) renderer.render(type);
            model.render(0.0625F);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glRotatef(-20F, 1F, 0F, 0F);
            GL11.glRotatef(-20F, 0F, 1F, 0F);
            GL11.glTranslatef(0.5F, -1.8F, -1F);
            if (renderer != null) renderer.render(type);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.5F, -1.8F, -1F);
            GL11.glScaled(0.8F, 0.8F, 0.8F);
            GL11.glRotatef(100F, 0F, 1F, 0F);
            GL11.glTranslatef(-0.35F, 0.0F, 0F);
            GL11.glRotatef(-10F, 1F, 0F, 0F);
            if (renderer != null) renderer.render(type);
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glScaled(0.8F, 0.8F, 0.8F);
            GL11.glTranslatef(0.0F, -1.8F, 0.0F);
            if (renderer != null) renderer.render(type);
        }
        if (type != IItemRenderer.ItemRenderType.INVENTORY)
            model.render(0.0625F);

    }

    public ModelBlockItemRenderer setRenderer(Custom renderer) {
        this.renderer = renderer;
        return this;
    }

    public interface Custom {
        void render(IItemRenderer.ItemRenderType type);
    }
}
