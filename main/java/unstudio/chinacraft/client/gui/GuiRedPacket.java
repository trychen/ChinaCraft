package unstudio.chinacraft.client.gui;

import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.inventory.ContainerRedPacket;

import java.util.Random;

public class GuiRedPacket extends GuiContainer {
    public String wish = "Best Wishes!";
    private int wishColor = Integer.MAX_VALUE;
    private String sender;
    private int currentItem;

    public GuiRedPacket(InventoryPlayer playerInv, ItemStack itemStack) {
        super(new ContainerRedPacket(playerInv, itemStack));
        currentItem = playerInv.currentItem;
        wishColor = new Random().nextInt(Integer.MAX_VALUE);
        NBTTagCompound par1NBTTagCompound = itemStack.getTagCompound();
        if (par1NBTTagCompound != null) {
            sender = par1NBTTagCompound.getString("Sender");
        }

        switch (new Random().nextInt(2)) {
            case 0:
                wish = StatCollector.translateToLocal("gui.redpacket.default.wash.1");
                break;
            case 1:
                wish = StatCollector.translateToLocal("gui.redpacket.default.wash.2");
                break;
            case 2:
                wish = StatCollector.translateToLocal("gui.redpacket.default.wash.3");
                break;
        }
    }


    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        String s = sender == null ? StatCollector.translateToLocal("item.redpacket.name") : (sender.equals("") ? StatCollector.translateToLocal("item.redpacket.name") : StatCollector.translateToLocal("gui.redpacket.from") + " " + sender + " " + StatCollector.translateToLocal("gui.redpacket.s") + " " + StatCollector.translateToLocal("item.redpacket.name")); // 设置Gui标题
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
    
    @Override
    protected boolean checkHotbarKeys(int p_146983_1_)
    {
    	if(p_146983_1_ == currentItem+2)return false;
    	else return super.checkHotbarKeys(p_146983_1_);
    }


    public void setWish(String s) {
        wish = s;
    }

    public String getWish() {
        return wish;
    }
}
