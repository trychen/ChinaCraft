package unstudio.chinacraft.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.client.model.ModelBuhrimill;
import unstudio.chinacraft.client.model.ModelExtendBlock;

/**
 * Use for nothing.
 * Created by trychen on 15/11/29.
 */
public class ItemBuhrimillRenderer implements IItemRenderer {
    private ModelExtendBlock model;
    private ResourceLocation texture;

    public ItemBuhrimillRenderer() {
        this.model = new ModelBuhrimill();
        this.texture = new ResourceLocation("chinacraft:textures/models/block/buhrimill.png");
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

        if (type == IItemRenderer.ItemRenderType.INVENTORY){
            GL11.glScalef(1.2F, 1.2F, 1.2F);
            GL11.glTranslatef(0.0F, -1.05F, 0.00F);
//            GL11.glRotatef(-45F, 0F, 1F, 0F);
            GL11.glRotatef(00F, 0F, 0F, 1F);
            model.render(0.0625F);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED){
            GL11.glRotatef(-20F, 1F, 0F, 0F);
            GL11.glRotatef(-20F, 0F, 1F, 0F);
            GL11.glTranslatef(0.5F, -1.8F, -1F);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON){
            GL11.glTranslatef(0.5F, -1.8F, -1F);
            GL11.glScaled(0.8F, 0.8F, 0.8F);
            GL11.glRotatef(100F, 0F, 1F, 0F);
            GL11.glTranslatef(-0.35F, 0.0F, 0F);
            GL11.glRotatef(-10F, 1F, 0F, 0F);
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY){
            GL11.glScaled(0.8F, 0.8F, 0.8F);
            GL11.glTranslatef(0.0F, -1.8F, 0.0F);

        }
        if (type != IItemRenderer.ItemRenderType.INVENTORY){
            model.render(0.0625F);
        }
//        GL11.glPushMatrix();
//        GL11.glScalef(-1.2F, -1.2F, 1.2F);
//        switch(type) {
//            case INVENTORY:
//                GL11.glTranslatef(0,-1f, 0);
//                break;
//            case EQUIPPED:
//                GL11.glTranslatef(-0.8F, -1.2F, -0.21F);
//                break;
////            case EQUIPPED_FIRST_PERSON:
////                GL11.glTranslatef(0.0F, -4.0F, -0.06F);
////                break;
//            case ENTITY:
//                GL11.glTranslatef(0.0F, -0.2F, 0.0F);
//                break;
//        }
//
//        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
//        this.model.render(0.0625F);
//        GL11.glPopMatrix();
    }
}
