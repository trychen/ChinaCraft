package unstudio.chinacraft.client.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.client.model.ModelExtendBlock;

/**
 * Use for nothing.
 * Created by trychen on 15/11/29.
 */
public class BlockModelItemRenderer implements IItemRenderer {
    private ModelExtendBlock model;
    private ResourceLocation texture;

    public BlockModelItemRenderer(ModelExtendBlock model, ResourceLocation texture) {
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
        GL11.glScalef(-1.2F, -1.2F, 1.2F);
        switch(type) {
            case INVENTORY:
                GL11.glTranslatef(0,-0.42F, 0);
                break;
            case EQUIPPED:
                GL11.glTranslatef(-0.8F, -1.2F, -0.21F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(0.0F, -4.0F, -0.06F);
                break;
            case ENTITY:
                GL11.glTranslatef(0.0F, -0.11F, 0.0F);
                break;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        this.model.render(0.0625F);
        GL11.glPopMatrix();
    }
}
