package unstudio.chinacraft.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.client.model.ModelExtendBlock;
import unstudio.chinacraft.client.model.ModelPotteryTable;

public class ItemPotteryTableRenderer implements IItemRenderer {
    private ModelExtendBlock model;
    private ResourceLocation texture;

    public ItemPotteryTableRenderer() {
        this.model = new ModelPotteryTable();
        this.texture = new ResourceLocation("chinacraft:textures/models/block/PotteryTable.png");
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glRotatef(180F, 1F, 0F, 0F);

        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            GL11.glTranslatef(0.0F, -0.55F, 0.00F);
            // GL11.glRotatef(-45F, 0F, 1F, 0F);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            model.render(0.0625F);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glScaled(0.6D, 0.6D, 0.6D);
            GL11.glRotatef(-20F, 1F, 0F, 0F);
            GL11.glRotatef(-20F, 0F, 1F, 0F);
            GL11.glTranslatef(0.5F, -1.3F, -1F);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.5F, -1.3F, -1F);
            GL11.glScaled(0.4D, 0.4D, 0.4D);
            GL11.glRotatef(100F, 0F, 1F, 0F);
            GL11.glTranslatef(-0.35F, 0.0F, 0F);
            GL11.glRotatef(-10F, 1F, 0F, 0F);
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glScaled(0.4F, 0.4F, 0.4F);
            GL11.glTranslatef(0.0F, -1.8F, 0.0F);
        }
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            model.render(0.0625F);
        }
    }
}
