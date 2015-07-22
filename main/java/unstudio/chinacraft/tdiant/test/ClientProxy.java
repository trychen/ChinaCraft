package unstudio.chinacraft.tdiant.test;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.CommonProxy;
import unstudio.chinacraft.renderer.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy { 
    public void init(FMLInitializationEvent event) {  	
    	super.init(event);
    	//tdiant add start--
    	FMLCommonHandler.instance().bus().register(this);
    	//tdiant add end--
    	
    }

	@SubscribeEvent
	public void keyListener(KeyInputEvent event) {
		//按B弹出GUI
		if (Keyboard.getEventKey() == Keyboard.KEY_B)
	        {
			Minecraft mc = Minecraft.getMinecraft();
			boolean a;
			mc.displayGuiScreen(new unstudio.chinacraft.gui.buhrimill(mc.currentScreen));a=true;
			if (a = true){System.out.println("GUI被成功调出");}else {System.out.println("GUI调用失败");}
	        }
	}
}
