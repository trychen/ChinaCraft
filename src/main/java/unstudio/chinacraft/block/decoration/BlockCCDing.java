package unstudio.chinacraft.block.decoration;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.block.model.BlockCCModel;
import unstudio.chinacraft.client.model.block.ModelDing;
import unstudio.chinacraft.client.render.item.ModelBlockItemRenderer;

/**
 * Created by trychen on 16/10/29.
 */
public class BlockCCDing extends BlockCCModel {
    public BlockCCDing() {
        super(Material.rock, ModelDing.class, "ding", Blocks.mossy_cobblestone.getIcon(0,0));
    }

    public static class ItemCustomRender implements ModelBlockItemRenderer.Custom{

        @Override
        public void render(IItemRenderer.ItemRenderType type) {
            if (type == IItemRenderer.ItemRenderType.INVENTORY){
                GL11.glScalef(0.65f,0.57f,0.65f);
                GL11.glTranslatef(-0.5f,1.2f,0);
                GL11.glRotatef(90F, 0F, 1F, 0F);
            }
        }
    }
}
