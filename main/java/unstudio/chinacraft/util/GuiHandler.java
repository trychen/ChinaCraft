package unstudio.chinacraft.util;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.inventory.*;
import unstudio.chinacraft.tileentity.*;


public class GuiHandler implements IGuiHandler{

        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                switch(ID)
                {
                case GuiID.GUI_Buhrimill:
                        return new ContainerBuhrimill(player.inventory, (TileBuhrimill) world.getTileEntity(x, y, z));
                        
                case GuiID.GUI_JadeBench:
                		return new ContainerJadeBench(player.inventory, (TileJadeBench) world.getTileEntity(x, y, z));
                		
                case GuiID.GUI_Cooker:
                	    return new ContainerCooker(player.inventory, (TileCooker) world.getTileEntity(x, y, z));
                	    
                case GuiID.GUI_Sericulture_Farme:
            	    return new ContainerSericultureFrame(player.inventory, (TileSericultureFrame) world.getTileEntity(x, y, z));
            	   
                case GuiID.GUI_PotteryTable:
            	    return new ContainerPotteryTable(player);
            	    
                case GuiID.GUI_PotteryKiln:
            	    return new ContainerPotteryKiln(player.inventory, (TilePotteryKiln) world.getTileEntity(x, y, z));
            	    
                case GuiID.GUI_RedPacket:
            	    return new ContainerRedPacket(player.inventory, player.inventory.getCurrentItem());
                }
                return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                switch(ID)
                {
                case GuiID.GUI_Buhrimill:
                        return new GuiBuhrimill(player.inventory, (TileBuhrimill) world.getTileEntity(x, y, z));
                        
                case GuiID.GUI_JadeBench:
                		return new GuiJadeBench(player.inventory, (TileJadeBench) world.getTileEntity(x, y, z));	
                		
                case GuiID.GUI_Cooker:
            		return new GuiCooker(player.inventory, (TileCooker) world.getTileEntity(x, y, z));	
            		
                case GuiID.GUI_Sericulture_Farme:
            	    return new GuiSericultureFrame(player.inventory, (TileSericultureFrame) world.getTileEntity(x, y, z));
            	    
                case GuiID.GUI_PotteryTable:
            	    return new GuiPotteryTable(player);
            	    
                case GuiID.GUI_PotteryKiln:
            	    return new GuiPotteryKiln(player.inventory, (TilePotteryKiln) world.getTileEntity(x, y, z));
            	    
                case GuiID.GUI_RedPacket:
            	    return new GuiRedPacket(player.inventory, player.inventory.getCurrentItem());
                }
                return null;
        }

}

