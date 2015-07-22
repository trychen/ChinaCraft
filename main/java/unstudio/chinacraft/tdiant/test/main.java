package unstudio.chinacraft.tdiant.test;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(name="TdiantTest",version="1.0.0",modid="tdianttest")
public class main {
	public static Block TdiantDebug_1 = new TdiantDebug_1();
@EventHandler
public static void pre(FMLInitializationEvent event)
{
	System.out.println("tdiant's test mod.");
	System.out.println("Please delete this mod when you want to ±‡“Î or ∑¢≤º.");
	TdiantDebug_1.setCreativeTab(CreativeTabs.tabRedstone);
}
}
