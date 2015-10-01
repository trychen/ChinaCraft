package unstudio.chinacraft.inventory;

import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.Random;

public class GuiRedPacket extends GuiContainer{
	public String wish = "Best Wishes!";
	private NBTTagCompound par1NBTTagCompound;
	private int wishColor = Integer.MAX_VALUE;
	public GuiRedPacket(InventoryPlayer playerInv,ItemStack itemStack) {
		super(new ContainerRedPacket(playerInv, itemStack));
		par1NBTTagCompound = itemStack.getTagCompound();
		if (par1NBTTagCompound != null){
			if (par1NBTTagCompound.getString("wash") == null) par1NBTTagCompound.setString("wash", StatCollector.translateToLocal("gui.redpacket.default.wash"));
		}
		wishColor=new Random().nextInt(Integer.MAX_VALUE);
	}


	@Override
	public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		String s = StatCollector.translateToLocal("item.redpacket.name"); // 设置Gui标题
		if(par1NBTTagCompound!=null) {
			wish = par1NBTTagCompound.getString("wash");
		}
		this.fontRendererObj.drawString(s, this.xSize / 2
				- this.fontRendererObj.getStringWidth(s) / 2, 5, Integer.MAX_VALUE);
		this.fontRendererObj.drawString(wish, this.xSize / 2
				- this.fontRendererObj.getStringWidth(wish) / 2, 68, wishColor);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/redpacket.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	public void setWish(String s){
		wish=s;
	}
	public String getWish(){
		return wish;
	}
}
