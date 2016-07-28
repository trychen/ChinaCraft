//石磨GUI
package unstudio.chinacraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.inventory.ContainerBuhrimill;
import unstudio.chinacraft.tileentity.TileBuhrimill;

public class GuiBuhrimill extends GuiContainer {

    private TileBuhrimill tileBuhrimill;

    public GuiBuhrimill(InventoryPlayer playerInv, TileBuhrimill tileEntity) {
        super(new ContainerBuhrimill(playerInv, tileEntity));
        this.tileBuhrimill = tileEntity;
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        String s = StatCollector.translateToLocal("gui.buhrimill.title"); // 设置Gui标题
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 3, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
                4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/buhrimill.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int b = tileBuhrimill.schedule;
        float max = tileBuhrimill.getMaxSchedule() * 1.0F;
        if (b > 0 && max > 0) {
            this.drawTexturedModalRect(k + 73, l + 25, 176, 0, (int) (24 * (b / max)), 16);
        }
    }
}
