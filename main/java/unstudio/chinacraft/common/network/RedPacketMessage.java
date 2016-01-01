package unstudio.chinacraft.common.network;

import java.io.IOException;

import unstudio.chinacraft.common.ChinaCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RedPacketMessage implements IMessage { //包类
    
    public ItemStack itemstack;

    public RedPacketMessage() {
    }
    
    public RedPacketMessage(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	try {
			itemstack = (new PacketBuffer(buf)).readItemStackFromBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	try {
			(new PacketBuffer(buf)).writeItemStackToBuffer(this.itemstack);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
