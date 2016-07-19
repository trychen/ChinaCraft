package unstudio.chinacraft.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import unstudio.chinacraft.client.gui.*;
import unstudio.chinacraft.inventory.*;
import unstudio.chinacraft.tileentity.*;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	BlockPos pos = new BlockPos(x, y, z);
        switch (ID) {
        case GuiID.GUI_Buhrimill:
            return new ContainerBuhrimill(player.inventory, (TileBuhrimill) world.getTileEntity(pos));

        case GuiID.GUI_JadeBench:
            return new ContainerJadeBench(player.inventory, (TileJadeBench) world.getTileEntity(pos));

        case GuiID.GUI_CookingBench:
            return new ContainerCookingBench(player.inventory, (TileCookingBench) world.getTileEntity(pos));

        case GuiID.GUI_Sericulture_Farme:
            return new ContainerSericultureFrame(player.inventory, (TileSericultureFrame) world.getTileEntity(pos));

        case GuiID.GUI_PotteryTable:
            return new ContainerPotteryTable(player);

        case GuiID.GUI_PotteryKiln:
            return new ContainerPotteryKiln(player.inventory, (TilePotteryKiln) world.getTileEntity(pos));

        case GuiID.GUI_RedPacket:
            return new ContainerRedPacket(player, player.inventory.getCurrentItem());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	BlockPos pos = new BlockPos(x, y, z);
        switch (ID) {
        case GuiID.GUI_Buhrimill:
            return new GuiBuhrimill(player.inventory, (TileBuhrimill) world.getTileEntity(pos));

        case GuiID.GUI_JadeBench:
            return new GuiJadeBench(player.inventory, (TileJadeBench) world.getTileEntity(pos));

        case GuiID.GUI_CookingBench:
            return new GuiCookingBench(player.inventory, (TileCookingBench) world.getTileEntity(pos));

        case GuiID.GUI_Sericulture_Farme:
            return new GuiSericultureFrame(player.inventory, (TileSericultureFrame) world.getTileEntity(pos));

        case GuiID.GUI_PotteryTable:
            return new GuiPotteryTable(player);

        case GuiID.GUI_PotteryKiln:
            return new GuiPotteryKiln(player.inventory, (TilePotteryKiln) world.getTileEntity(pos));

        case GuiID.GUI_RedPacket:
            return new GuiRedPacket(player, player.inventory.getCurrentItem());
        case GuiID.GUI_UpData:
            return new GuiUpdata();
        }
        return null;
    }

}
