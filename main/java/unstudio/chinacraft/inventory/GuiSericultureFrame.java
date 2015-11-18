package unstudio.chinacraft.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.tileentity.TileSericultureFrame;

public class GuiSericultureFrame extends GuiContainer {

	private TileSericultureFrame tile;
	
	public GuiSericultureFrame(InventoryPlayer playerInv, TileSericultureFrame tileEntity) {
		super(new ContainerSericultureFrame(playerInv,tileEntity));
		this.tile = tileEntity;
	}

	@Override
	public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		String s = StatCollector.translateToLocal("gui.sericulture_frame.title"); // 设置Gui标题
		this.fontRendererObj.drawString(s, this.xSize / 2
				- this.fontRendererObj.getStringWidth(s) / 2, 3, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/sericulture_frame.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
