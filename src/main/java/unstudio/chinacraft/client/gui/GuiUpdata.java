package unstudio.chinacraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * 自动更新界面 GUI
 */
public class GuiUpdata extends GuiScreen {
    public static final int btnClose = 0;
    public static final int btnDownload = 1;

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(btnClose, width - this.fontRendererObj.getStringWidth("下载"), height - height,
                this.fontRendererObj.getStringWidth("下载") + 10, 20, "下载"));
        this.buttonList.add(new GuiButton(btnDownload, width / 2 - 32, 50, 64, 20, "test"));
        this.drawString(this.fontRendererObj, "Test", width / 2, height / 2, 500);
    }

    @Override
    public void drawDefaultBackground() {

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
        case btnClose:
            this.mc.thePlayer.closeScreen();
            break;
        case btnDownload:
            System.out.println(0);
            break;
        }
    }
}
