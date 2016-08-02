package unstudio.chinacraft.client.render.block;

@Deprecated
public class BlockWoodenBucketRenderer /*implements ISimpleBlockRenderingHandler*/ {
/* TODO Buggy, disabled
    public final static int renderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        int l = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f = (l >> 16 & 255) / 255.0F;
        float f1 = (l >> 8 & 255) / 255.0F;
        float f2 = (l & 255) / 255.0F;
        float f4;

        if (EntityRenderer.anaglyphEnable) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        tessellator.setColorOpaque_F(f, f1, f2);
        IIcon iicon1 = ((BlockWoodenBucket) block).getInner();
        f4 = 0.125F;
        renderer.renderFaceXPos(block, x - 1.0F + f4, y, z, iicon1);
        renderer.renderFaceXNeg(block, x + 1.0F - f4, y, z, iicon1);
        renderer.renderFaceZPos(block, x, y, z - 1.0F + f4, iicon1);
        renderer.renderFaceZNeg(block, x, y, z + 1.0F - f4, iicon1);
        renderer.renderFaceYPos(block, x, y - 1.0F + 0.125F, z, iicon1);

        int i1 = world.getBlockMetadata(x, y, z);

        if (i1 == 1) {
            IIcon iicon = BlockLiquid.getLiquidIcon("water_still");
            renderer.renderFaceYPos(block, x, y - 1.0F + 0.875F, z, iicon);
        } else {

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
*/
}
