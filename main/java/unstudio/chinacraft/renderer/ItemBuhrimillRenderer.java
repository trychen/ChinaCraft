package unstudio.chinacraft.renderer;

import unstudio.chinacraft.block.model.ModelBuhrimill;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemBuhrimillRenderer implements IItemRenderer {

	private ModelBuhrimill modelBuhrimill;
	private TileEntity te;
	private double x, y, z;

	public ItemBuhrimillRenderer(TileEntity tileentity, double xloc, double yloc, double zloc) {
		modelBuhrimill = new ModelBuhrimill();
		if (tileentity == null) {
			return;
		}
		te = tileentity;
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
		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, x, y, z, 0.0F);
	}

}
