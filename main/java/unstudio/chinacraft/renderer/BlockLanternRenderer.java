package unstudio.chinacraft.renderer;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockLanternRenderer implements ISimpleBlockRenderingHandler{
	
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
            float f = 0.0625F;
            renderer.renderAllFaces = true;
//         renderer.setOverrideBlockTexture(renderer.getBlockIcon(ChinaCraft.bambooBlock));
            renderer.setRenderBounds(f*3, 0.0D, f*3, f*13, 1.0D, f*13);
            renderer.renderStandardBlock(block, x, y, z);
            IIcon iicon1 = block.getBlockTextureFromSide(0);
            renderer.renderFaceXPos(block, (double)((float)x - 1.0F), (double)y, (double)z, iicon1);
            renderer.renderFaceXNeg(block, (double)((float)x + 1.0F), (double)y, (double)z, iicon1);
            renderer.renderFaceZPos(block, (double)x, (double)y, (double)((float)z - 1.0F), iicon1);
            renderer.renderFaceZNeg(block, (double)x, (double)y, (double)((float)z + 1.0F), iicon1);
            IIcon iicon2 = block.getBlockTextureFromSide(2);
            renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F), (double)z, iicon2);
            renderer.renderFaceYNeg(block, (double)x, (double)((float)y + 1.0F), (double)z, iicon2);
//        renderer.setOverrideBlockTexture(renderer.getBlockIcon(Blocks.glass));
//        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
//        renderer.renderStandardBlock(block, x, y, z);
//        renderer.setOverrideBlockTexture(renderer.getBlockIcon(Blocks.obsidian));
//        renderer.setRenderBounds(0.125D, 0.0062500000931322575D, 0.125D, 0.875D, (double)f, 0.875D);
//        renderer.renderStandardBlock(block, x, y, z);
//        renderer.setOverrideBlockTexture(renderer.getBlockIcon(Blocks.beacon));
//        renderer.setRenderBounds(0.1875D, (double)f, 0.1875D, 0.8125D, 0.875D, 0.8125D);
//        renderer.renderStandardBlock(block, x, y, z);
           renderer.renderAllFaces = false;
           renderer.clearOverrideBlockTexture();
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
	
	public static void drawInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		Tessellator tes = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tes.startDrawingQuads();
		tes.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tes.draw();
		tes.startDrawingQuads();
		tes.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tes.draw();
		tes.startDrawingQuads();
		tes.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tes.draw();
		tes.startDrawingQuads();
		tes.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tes.draw();
		tes.startDrawingQuads();
		tes.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tes.draw();
		tes.startDrawingQuads();
		tes.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.overrideBlockTexture!=null?renderer.overrideBlockTexture: renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tes.draw();
		GL11.glPopMatrix();
	}

}
