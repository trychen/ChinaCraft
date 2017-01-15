package unstudio.chinacraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.inventory.ContainerRedPacket;

/**
 * 红包的GUI
 */
public class GuiRedPacket extends GuiContainer {

    private GuiTextField wishTextBox;
    private GuiTextField sendeeTextBox;

    private int wishColor = Integer.MAX_VALUE;
    private String sender="";
    private int currentItem;
    private EntityPlayer player;
    private ItemStack itemStack;
    private GuiButton buttonSend;

    public GuiRedPacket(EntityPlayer player, ItemStack itemStack) {
        super(new ContainerRedPacket(player, itemStack));
        this.itemStack = itemStack;
        this.player = player;
        currentItem = player.inventory.currentItem;
        NBTTagCompound par1NBTTagCompound = itemStack.getTagCompound();
        if (par1NBTTagCompound != null) {
            NBTTagCompound redpacket = par1NBTTagCompound.getCompoundTag("redpacket");
            if (redpacket != null) {
                sender = redpacket.getString("sender");
            }
        }
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2 - 80, 65, 160, 16);
        wishTextBox.setFocused(true);
        wishTextBox.setMaxStringLength(64);
        sendeeTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2 + 96, 65, 64, 16);
        sendeeTextBox.setFocused(false);
        sendeeTextBox.setMaxStringLength(32);
        this.buttonList.add(this.buttonSend = new GuiButton(0, k + this.xSize / 2 + 96, l + 89, 64, 20,
                StatCollector.translateToLocal("gui.redpacket.send")));
        buttonSend.enabled = false;
        updateButton();
        NBTTagCompound par1NBTTagCompound = itemStack.getTagCompound();
        if (par1NBTTagCompound != null) {
            NBTTagCompound redpacket = par1NBTTagCompound.getCompoundTag("redpacket");
            if (redpacket != null) {
                String wish = redpacket.getString("wish");
                if (wish == null || wish.isEmpty())
                    wishTextBox.setText(StatCollector.translateToLocal("gui.redpacket.wash"));
                else
                    wishTextBox.setText(wish);
                sendeeTextBox.setText(redpacket.getString("sendee"));
            }
        }
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        String s = sender == null ? StatCollector.translateToLocal("item.redpacket.name")
                : (sender.isEmpty() ? StatCollector.translateToLocal("item.redpacket.name")
                        : StatCollector.translateToLocal("gui.redpacket.from").replaceAll("%sender%", sender)); // 设置Gui标题
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 5,
                Integer.MAX_VALUE);
        if (sender == null || sender.isEmpty() || sender.equalsIgnoreCase(player.getDisplayName())) {
            sendeeTextBox.setEnabled(true);
            wishTextBox.setEnabled(true);
            wishTextBox.drawTextBox();
            sendeeTextBox.drawTextBox();
            buttonSend.visible = true;
        } else {
            sendeeTextBox.setEnabled(false);
            sendeeTextBox.setVisible(false);
            wishTextBox.setEnabled(false);
            wishTextBox.setVisible(false);
            buttonSend.visible = false;
            this.fontRendererObj.drawString(getWish(), this.xSize / 2 - this.fontRendererObj.getStringWidth(getWish()) / 2, 68,
                    Integer.MAX_VALUE);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/redpacket.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected boolean checkHotbarKeys(int p_146983_1_) {
        if (p_146983_1_ == currentItem + 2)
            return false;
        else
            return super.checkHotbarKeys(p_146983_1_);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        wishTextBox.textboxKeyTyped(par1, par2);
        sendeeTextBox.textboxKeyTyped(par1, par2);
        if (par1 == '\r') {

        }
        if (par2 == 1) {
            player.closeScreen();
            sendRedPacketToServer(false);
        }

        updateButton();
    }

    public String getWish() {
        return wishTextBox.getText().replaceAll("&", "§");
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }

    private void sendRedPacketToServer(boolean isSend) {
        ChinaCraft.Network.sendToServer(new RedPacketMessage(isSend?player.getDisplayName():sender,getWish(),sendeeTextBox.getText(),isSend));
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox.mouseClicked(par1 - k, par2 - l, par3);
        sendeeTextBox.mouseClicked(par1 - k, par2 - l, par3);
        super.mouseClicked(par1, par2, par3);
        // 同样,如果你有多个文本框,则每个都要来一遍
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 0) {
                player.closeScreen();
                sendRedPacketToServer(true);
            }
        }
    }

    private void updateButton() {
        buttonSend.enabled = sendeeTextBox.getText() != null && !sendeeTextBox.getText().isEmpty();
    }
}
