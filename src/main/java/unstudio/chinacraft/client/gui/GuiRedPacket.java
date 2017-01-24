package unstudio.chinacraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    private GuiButton buttonSend;

    private int wishColor = Integer.MAX_VALUE;
    private String sender="",wish="";
    private int currentItem;
    private EntityPlayer player;

    public GuiRedPacket(EntityPlayer player) {
        super(new ContainerRedPacket(player));
        this.player = player;
        ItemStack itemStack = player.getHeldItem();
        currentItem = player.inventory.currentItem;
        if (itemStack.hasTagCompound()&&itemStack.getTagCompound().hasKey("redpacket")) {
                NBTTagCompound redpacket = itemStack.getTagCompound().getCompoundTag("redpacket");
                sender = redpacket.getString("sender");
                wish = redpacket.getString("wish");
        }
        if (wish == null || wish.isEmpty()) wish=StatCollector.translateToLocal("gui.redpacket.wash");
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
        wishTextBox.setText(wish);
        sendeeTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2 + 96, 65, 64, 16);
        sendeeTextBox.setMaxStringLength(32);
        this.buttonList.add(this.buttonSend = new GuiButton(0, k + this.xSize / 2 + 96, l + 89, 64, 20,
                StatCollector.translateToLocal("gui.redpacket.send")));
        buttonSend.enabled = false;
        updateButton();
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (sender == null || sender.isEmpty() || sender.equalsIgnoreCase(player.getDisplayName())) {
            wishTextBox.drawTextBox();
            sendeeTextBox.drawTextBox();
            String head = StatCollector.translateToLocal("item.redpacket.name");
            this.fontRendererObj.drawString(head, this.xSize / 2 - this.fontRendererObj.getStringWidth(head) / 2, 5,
                    Integer.MAX_VALUE);
        } else {
            sendeeTextBox.setEnabled(false);
            sendeeTextBox.setVisible(false);
            wishTextBox.setEnabled(false);
            wishTextBox.setVisible(false);
            buttonSend.visible = false;
            this.fontRendererObj.drawString(getWish(), this.xSize / 2 - this.fontRendererObj.getStringWidth(getWish()) / 2, 68,
                    Integer.MAX_VALUE);
            String head = String.format(StatCollector.translateToLocal("gui.redpacket.from"),sender);
            this.fontRendererObj.drawString(head, this.xSize / 2 - this.fontRendererObj.getStringWidth(head) / 2, 5,
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
            if(wishTextBox.isFocused()) sendeeTextBox.setFocused(true);
        }if (par2 == 1) {
            player.closeScreen();
            sendRedPacketToServer(false);
        }

        updateButton();
    }

    public String getWish() {
        return wishTextBox.getText();
    }

    public String getSendee(){
        return sendeeTextBox.getText();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }

    @SideOnly(Side.CLIENT)
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
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(!button.enabled) return;
        switch (button.id){
            case 0:{
                sendRedPacketToServer(true);
                player.closeScreen();
                break;
            }
            default:
                break;
        }
    }

    private void updateButton() {
        buttonSend.enabled = sendeeTextBox.getText() != null && !sendeeTextBox.getText().isEmpty();
    }
}
