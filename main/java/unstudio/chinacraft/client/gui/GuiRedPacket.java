package unstudio.chinacraft.client.gui;

import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.inventory.ContainerRedPacket;

import java.util.Random;

public class GuiRedPacket extends GuiContainer {
	
    private String wish="";
    private double money=0;
    private String sendee;
    private GuiTextField wishTextBox;
    private GuiTextField moneyTextBox;
    private GuiTextField sendeeTextBox;
    
    private ContainerRedPacket container;
    private int wishColor = Integer.MAX_VALUE;
    private String sender;
    private int currentItem;
    private EntityPlayer player;
    private ItemStack itemstack;

    public GuiRedPacket(EntityPlayer player, ItemStack itemStack) {
    	super(new ContainerRedPacket(player, itemStack));
    	this.itemstack = itemStack;
    	this.player = player;
    	if(player.openContainer instanceof ContainerRedPacket){
    		container = (ContainerRedPacket) player.openContainer;
    	}
        currentItem = player.inventory.currentItem;
        NBTTagCompound par1NBTTagCompound = itemStack.getTagCompound();
        if (par1NBTTagCompound != null) {
        	NBTTagCompound redpacket=par1NBTTagCompound.getCompoundTag("Redpacket");
        	if(redpacket != null){
	            sender = redpacket.getString("Sender");
	            wish = redpacket.getString("Wish");
	            money = redpacket.getDouble("Money");
	            sendee = redpacket.getString("Sendee");
        	}
        }   
    }

    @Override
    public void initGui() {
    	Keyboard.enableRepeatEvents(true);
    	wishTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2-80, 65, 160, 16);
    	wishTextBox.setFocused(true);
    	wishTextBox.setText(wish);
    	wishTextBox.setMaxStringLength(128);
    	super.initGui();
    }
    
    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        String s = sender == null ? StatCollector.translateToLocal("item.redpacket.name") : (sender.equals("") ? StatCollector.translateToLocal("item.redpacket.name") : StatCollector.translateToLocal("gui.redpacket.from").replaceAll("%sender%", sender)); // 设置Gui标题
        this.fontRendererObj.drawString(s, this.xSize / 2
                - this.fontRendererObj.getStringWidth(s) / 2, 5, Integer.MAX_VALUE);
        this.fontRendererObj.drawString(wish, this.xSize / 2
                - this.fontRendererObj.getStringWidth(wish) / 2, 68, Integer.MAX_VALUE);
        wishTextBox.drawTextBox();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/redpacket.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
    
    @Override
    protected boolean checkHotbarKeys(int p_146983_1_)
    {
    	if(p_146983_1_ == currentItem+2)return false;
    	else return super.checkHotbarKeys(p_146983_1_);
    }
    
    @Override
    protected void keyTyped(char par1, int par2)
    {
    	wishTextBox.textboxKeyTyped(par1, par2);
    	wish=wishTextBox.getText();
    	if (par1 == '\r')
    	{
    		
    	}
    	if (par2 == 1){
    		player.closeScreen();
    		// this.mc.displayGuiScreen((GuiScreen)null);
    		sendRedPacketToServer(false);
    	}
    }

    public void setWish(String s) {
        wish = s;
    }

    public String getWish() {
        return wish;
    }
    
    @Override
    public void onGuiClosed() {
    	Keyboard.enableRepeatEvents(false);
    	super.onGuiClosed();
    }
    
    private void sendRedPacketToServer(boolean isSend){
    	NBTTagCompound redpacket = new NBTTagCompound();
    	if(((wish!=null&&wish.length()>0)||(money>0))&&(sender!=null&&sender.length()>0))redpacket.setString("Sender", sender);
    	else if(((wish!=null&&wish.length()>0)||(money>0))&&(isSend&&sendee.length()>0)) redpacket.setString("Sender", player.getDisplayName());
    	if(wish!=null&&wish.length()>0)redpacket.setString("Wish", wish);
    	if(isSend&&sendee.length()>0)redpacket.setString("Sendee", sendee);
    	if(money>0)redpacket.setDouble("Money", money);
    	itemstack.setTagInfo("Redpacket", redpacket);
    	ChinaCraft.Network.sendToServer(new RedPacketMessage(itemstack));
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3){
    	super.mouseClicked(par1, par2, par3);
    	wishTextBox.mouseClicked(par1, par2, par3);
    	//同样,如果你有多个文本框,则每个都要来一遍
    }

    
    protected void actionPerformed(GuiButton button)
    {
    
    }
}
