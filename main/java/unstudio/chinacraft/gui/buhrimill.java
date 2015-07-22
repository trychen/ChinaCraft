//Power by tdiant
//石磨GUI
package unstudio.chinacraft.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class buhrimill extends GuiScreen {
	 private GuiScreen parentScreen;
	 private GuiTextField tfInput;
	 
	 @Override
	 public boolean doesGuiPauseGame() {  //让GUI在单人模式下不会暂停游戏保存存档
	     return false;
	 }
	 
	    public buhrimill(GuiScreen currentScreen) {
		// TODO 自动生成的构造函数存根
	}
	 
	    public void initGui()
	    {
	    	Keyboard.enableRepeatEvents(true);
	    	tfInput = new GuiTextField(fontRendererObj, (int)(width*0.5)-150, (int)(height*0.85), 300, 20);
	    	tfInput.setMaxStringLength(64);
	    	tfInput.setFocused(false);
	    	tfInput.setCanLoseFocus(true); 
	    }
	 
	    public void drawScreen(int par1, int par2, float par3)
	    {
	        drawDefaultBackground();
	        //会被控件(即按键)盖住.
	        super.drawScreen(par1,par2,par3);
	        //在控件(即按键)之上.
	        tfInput.drawTextBox();
	    }
}
