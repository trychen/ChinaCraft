package unstudio.chinacraft.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import unstudio.chinacraft.block.model.ModelPotteryBase;

public class ItemPotteryBlockRenderer implements IItemRenderer {

	private ModelPotteryBase model;
	private TileEntity tile;
	private double x, y, z;

	public ItemPotteryBlockRenderer(TileEntity tileentity, double xloc, double yloc, double zloc) {
		model = new ModelPotteryBase();
		if (tileentity == null) {
			return;
		}
		tile = tileentity;
		x = xloc;
		y = yloc;
		z = zloc;
	}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, x, y, z, 0.0F);
	}
}
