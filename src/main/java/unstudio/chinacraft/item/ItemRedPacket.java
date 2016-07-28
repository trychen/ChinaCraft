package unstudio.sinocraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import unstudio.sinocraft.client.gui.GuiID;
import unstudio.sinocraft.common.SinoCraft;

public class ItemRedPacket extends Item {

    public ItemRedPacket() {
        setCreativeTab(SinoCraft.tabCore);
        setUnlocalizedName("redpacket");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        entityPlayer.openGui(SinoCraft.instance, GuiID.GUI_RedPacket, world, entityPlayer.chunkCoordX,
                entityPlayer.chunkCoordY, entityPlayer.chunkCoordZ);

        return itemStack;
    }

}
