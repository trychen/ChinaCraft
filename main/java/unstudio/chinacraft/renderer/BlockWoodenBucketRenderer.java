package unstudio.chinacraft.renderer;

import unstudio.chinacraft.block.BlockWoodenBucket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockWoodenBucketRenderer implements ISimpleBlockRenderingHandler{

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
        int l = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;
        float f4;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        tessellator.setColorOpaque_F(f, f1, f2);
        IIcon iicon1 = ((BlockWoodenBucket)block).getInner();
        f4 = 0.125F;
        renderer.renderFaceXPos(block, (double)((float)x - 1.0F + f4), (double)y, (double)z, iicon1);
        renderer.renderFaceXNeg(block, (double)((float)x + 1.0F - f4), (double)y, (double)z, iicon1);
        renderer.renderFaceZPos(block, (double)x, (double)y, (double)((float)z - 1.0F + f4), iicon1);
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)((float)z + 1.0F - f4), iicon1);
        renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + 0.125F), (double)z, iicon1);
        
        int i1 = world.getBlockMetadata(x, y, z);

        if (i1 == 1)
        {
            IIcon iicon = BlockLiquid.getLiquidIcon("water_still");
            renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + 0.875F), (double)z, iicon);
        }else {
        	
        }
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

}
