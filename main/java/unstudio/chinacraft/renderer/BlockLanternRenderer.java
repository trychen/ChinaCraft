package unstudio.chinacraft.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.block.Lantern;

public class BlockLanternRenderer implements ISimpleBlockRenderingHandler {

    public static int renderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId,
                                     RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
                                    Block block, int modelId, RenderBlocks renderer) {
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        IIcon iicon1 = ((Lantern) block).getIconSide();
        float f4 = 0.0625F * 13;
        renderer.renderFaceXPos(block, x - 1.0F + f4, y, z, iicon1);
        renderer.renderFaceXNeg(block, x + 1.0F - f4, y, z, iicon1);
        renderer.renderFaceZPos(block, x, y, z - 1.0F + f4, iicon1);
        renderer.renderFaceZNeg(block, x, y, z + 1.0F - f4, iicon1);
        IIcon iicon2 = ((Lantern) block).getIconTop();
        renderer.renderFaceYPos(block, x, y, z, iicon2);
        renderer.renderFaceYNeg(block, x, y, z, iicon2);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }

    public static void drawInventoryBlock(Block block, int metadata, RenderBlocks renderer) {
        Tessellator tes = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tes.startDrawingQuads();
        tes.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tes.draw();
        tes.startDrawingQuads();
        tes.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tes.draw();
        tes.startDrawingQuads();
        tes.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tes.draw();
        tes.startDrawingQuads();
        tes.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tes.draw();
        tes.startDrawingQuads();
        tes.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tes.draw();
        tes.startDrawingQuads();
        tes.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture != null ? renderer.overrideBlockTexture : renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tes.draw();
        GL11.glPopMatrix();
    }

}
