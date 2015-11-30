package unstudio.chinacraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.block.model.ModelExtendBlock;

/**
 * Use for nothing.
 * Created by trychen on 15/11/29.
 */
public class ModelBlockItemRenderer implements IItemRenderer {
    private ModelExtendBlock model;
    private ResourceLocation texture;

    public ModelBlockItemRenderer(ModelExtendBlock model,ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        switch(type) {
            case INVENTORY:
                GL11.glScalef(-1.2F, -1.2F, 1.2F);
                GL11.glTranslatef(0,-0.36F, 0);
                break;
            case EQUIPPED:
                GL11.glScalef(-0.6F, -0.6F, 0.6F);
                GL11.glTranslatef(-0.8F, -0.2F, 0.7F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glScalef(-0.6F, -0.6F, 0.6F);
                GL11.glTranslatef(0, -0.7F, 0.7F);
                break;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        this.model.render(0.0625F);
        GL11.glPopMatrix();
    }
}
