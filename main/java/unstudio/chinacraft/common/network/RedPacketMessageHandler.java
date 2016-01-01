package unstudio.chinacraft.common.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RedPacketMessageHandler implements IMessageHandler<RedPacketMessage, IMessage> { //包处理类
	@Override
	public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		ItemStack itemstack1 = player.inventory.getCurrentItem();
        if (itemstack1 == null)
        {
            return null;
        }

        if (itemstack1.getItem() == ChinaCraft.redPacket && itemstack1.getItem() == itemstack1.getItem())
        {
            itemstack1.setTagInfo("Redpacket", message.itemstack.getTagCompound().getCompoundTag("Redpacket"));
        }
        return null; 
	}
}
