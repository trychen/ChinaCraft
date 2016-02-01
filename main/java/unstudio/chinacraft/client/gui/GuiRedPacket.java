package unstudio.chinacraft.client.gui;

import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.inventory.ContainerRedPacket;
import unstudio.forgebukkitbridge.ServerManager;

public class GuiRedPacket extends GuiContainer {
	
    private String wish="";
    private double money=0.00;
    private String sendee="";
    private GuiTextField wishTextBox;
    private GuiTextField moneyTextBox;
    private GuiTextField sendeeTextBox;
    
    private ContainerRedPacket container;
    private int wishColor = Integer.MAX_VALUE;
    private String sender;
    private int currentItem;
    private EntityPlayer player;
    private ItemStack itemstack;
    private GuiButton buttonSend;

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
	            if(wish==null||wish.isEmpty())wish=StatCollector.translateToLocal("gui.redpacket.wash");
	            money = redpacket.getDouble("Money");
	            sendee = redpacket.getString("Sendee");
	            if(sendee!=null||sendee.length()==0||sendee.equalsIgnoreCase(player.getDisplayName())){
	            	if(ChinaCraft.vault!=null){
	            		ChinaCraft.vault.depositPlayer(player.getDisplayName(), money);
	            	}
	            }
        	}
        }   
    }

    @Override
    public void initGui() {
    	this.buttonList.clear();
    	Keyboard.enableRepeatEvents(true);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
    	wishTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2-80, 65, 160, 16);
    	wishTextBox.setFocused(true);
    	wishTextBox.setText(wish);
    	wishTextBox.setMaxStringLength(64);
    	moneyTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2+96, 41, 64, 16);
    	moneyTextBox.setFocused(false);
    	moneyTextBox.setText(String.valueOf(money));
    	moneyTextBox.setMaxStringLength(32);
    	sendeeTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.xSize / 2+96, 65, 64, 16);
    	sendeeTextBox.setFocused(false);
    	sendeeTextBox.setText(sendee);
    	sendeeTextBox.setMaxStringLength(32);
    	this.buttonList.add(this.buttonSend = new GuiButton(0, k+this.xSize / 2+96, l+89, 64, 20, StatCollector.translateToLocal("gui.redpacket.send")));
    	buttonSend.enabled=false;
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
        String s = sender == null ? StatCollector.translateToLocal("item.redpacket.name") : (sender.equals("") ? StatCollector.translateToLocal("item.redpacket.name") : StatCollector.translateToLocal("gui.redpacket.from").replaceAll("%sender%", sender)); // 设置Gui标题
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 5, Integer.MAX_VALUE);
        if(sender==null||sender.isEmpty()){
        	sendeeTextBox.setEnabled(true);
        	if(ChinaCraft.vault!=null){
        		moneyTextBox.setEnabled(true);
        		moneyTextBox.drawTextBox();
        	}else{
        		moneyTextBox.setEnabled(false);
        	}
        	wishTextBox.setEnabled(true);
        	wishTextBox.drawTextBox();
	        sendeeTextBox.drawTextBox();
	        buttonSend.visible=true;
        }else{
        	sendeeTextBox.setEnabled(false);
        	moneyTextBox.setEnabled(false);
        	wishTextBox.setEnabled(false);
        	buttonSend.visible=false;
        	this.fontRendererObj.drawString(wish, this.xSize / 2 - this.fontRendererObj.getStringWidth(wish) / 2, 68, Integer.MAX_VALUE);
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
    protected boolean checkHotbarKeys(int p_146983_1_)
    {
    	if(p_146983_1_ == currentItem+2)return false;
    	else return super.checkHotbarKeys(p_146983_1_);
    }
    
    @Override
    protected void keyTyped(char par1, int par2)
    {
    	wishTextBox.textboxKeyTyped(par1, par2);
    	wish=wishTextBox.getText().replaceAll("&", "§");
    	moneyTextBox.textboxKeyTyped(par1, par2);
    	try{
    		money=Double.valueOf(moneyTextBox.getText());
    	}catch(NumberFormatException error){
    		money=0.00;
    		moneyTextBox.setText("0.00");
    	}
    	sendeeTextBox.textboxKeyTyped(par1, par2);
    	sendee=sendeeTextBox.getText();
    	
    	if (par1 == '\r')
    	{

    	}
    	if (par2 == 1){
    		player.closeScreen();
    		// this.mc.displayGuiScreen((GuiScreen)null);
    		sendRedPacketToServer(false);
    	}
    	
    	updateButton();
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
    	if(isSend&&sendee!=null&&sendee.length()>0)redpacket.setString("Sendee", sendee);
    	if(ChinaCraft.vault!=null&&money>0){
    		if(ChinaCraft.vault.withdrawPlayer(sender, money)){
    			redpacket.setDouble("Money", money);
    		}else{
    			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("redpacket.not_enough_money")));
    			redpacket.setDouble("Money", 0.0);
    		}
    	}else{
    		redpacket.setDouble("Money", 0.0);
    	}
    	itemstack.setTagInfo("Redpacket", redpacket);
    	ChinaCraft.Network.sendToServer(new RedPacketMessage(itemstack));
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3){
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
    	wishTextBox.mouseClicked(par1-k, par2-l, par3);
    	moneyTextBox.mouseClicked(par1-k, par2-l, par3);
    	sendeeTextBox.mouseClicked(par1-k, par2-l, par3);
    	super.mouseClicked(par1, par2, par3);
    	//同样,如果你有多个文本框,则每个都要来一遍
    }

    
    @Override
	protected void actionPerformed(GuiButton button)
    {
    	if(button.enabled){
    		if(button.id==0){
        		player.closeScreen();
        		sendRedPacketToServer(true);
    		}
    	}
    }
    
    private void updateButton(){
    	buttonSend.enabled = (sendee!=null&&sendee.length()>0);
    }
}
