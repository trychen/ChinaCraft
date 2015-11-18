package unstudio.chinacraft.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.tileentity.TileCooker;

public class GuiCooker extends GuiContainer {

	private TileCooker tileCooker;
	
	public GuiCooker(InventoryPlayer playerInv, TileCooker tileEntity) {
		super(new ContainerCooker(playerInv,tileEntity));
		this.tileCooker = tileEntity;
	}

	@Override
	public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		String s = StatCollector.translateToLocal("gui.cooker.title"); // 设置Gui标题
		this.fontRendererObj.drawString(s, this.xSize / 2
				- this.fontRendererObj.getStringWidth(s) / 2, 3, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/cooker.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.tileCooker.isBurning())
        {
            int i1 = this.tileCooker.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 81, l + 14 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }
	}
}
