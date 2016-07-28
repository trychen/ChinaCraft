package unstudio.chinacraft.client.render.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by trychen on 16/7/26.
 */
public class SpearItemRender implements IItemRenderer{

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return (type == ItemRenderType.ENTITY) || (type == ItemRenderType.EQUIPPED) || (type == ItemRenderType.EQUIPPED_FIRST_PERSON);
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return (type == ItemRenderType.ENTITY) && ((helper == ItemRendererHelper.ENTITY_ROTATION) || (helper == ItemRendererHelper.ENTITY_BOBBING));
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object[] data) {
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            GL11.glTranslatef(0f,-0.14f,0.1f);
            IIcon iicon = ((EntityLivingBase) data[1]).getItemIcon(item, 0);
            HugeItemRenderer.renderGiantItemEquipped(iicon, item);
        } else if (type == ItemRenderType.EQUIPPED) {
            float scale = 1.9F;
            GL11.glScalef(scale, scale, scale);

            GL11.glTranslatef(-0.275F, -0.2135F, 0.03F);
            IIcon iicon = ((EntityLivingBase) data[1]).getItemIcon(item, 0);
            HugeItemRenderer.renderGiantItemEquipped(iicon, item);
        } else if (type == ItemRenderType.ENTITY) {
            GL11.glScalef(3f, 3f, 3f);

            GL11.glTranslatef(0.0F, 0.15F, 0.0F);

            EntityItem entityItem = (EntityItem) data[1];

            HugeItemRenderer.renderDroppedItem(entityItem, item);
        }
    }
}
