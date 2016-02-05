package unstudio.chinacraft.api.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BaseMessage implements IMessage { //包类
	    
	    private String text;

	    public BaseMessage(String text) {
	        this.text = text;
	    }

	    @Override
	    public void fromBytes(ByteBuf buf) {
	        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
	    }

	    @Override
	    public void toBytes(ByteBuf buf) {
	        ByteBufUtils.writeUTF8String(buf, text);
	    }

	    public static class Handler implements IMessageHandler<BaseMessage, IMessage> { //包处理类
			@Override
			public IMessage onMessage(BaseMessage message, MessageContext ctx) {
				System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
	            return null; 
			}
	    }
}
/*
	注册一个包
	Packets are done via separate classes for every Packet-ID (in the code also referred to as "discriminator"). If you are used to the huge switch-case statements like many people did it in 1.6, well, it's time to move on.
	For each Packet you want to send, we are going to need 2 classes, one for the Packet (implementing IMessage) and one for handling the Packet (implementing IMessageHandler). For a cleaner setup I usually implement the handler as an inner class of the Packet (see below).
	To register your Packet, call registerMessage(MyMessageHandler.class, MyMessage.class, packetID, receivingSide) on the wrapper you obtained. The first two parameters should be self-explanatory. PacketID is the same as it was in 1.6, usually you can start at 0 and just increment by one for each packet you add. Keep in mind that the maximum PacketID you can use is 255. The receiving side is, well, the side that should receive the packet. Can be one of Side.CLIENT or Side.SERVER.

	执行包类
	The IMessage interface requires you to implement two methods. fromBytes and toBytes. You can think of the IMessage as a blob of data that gets send over the network. Because the network is only a stream bytes, you have to serialize and deserialize your message into this stream of bytes. That is what these two methods do. toBytes writes your data to the stream (use the write*** methods on the ByteBuf for that) and fromBytes reads your data back into the message class (use the read*** methods for that).
	Keep in mind that your packet class must always have a default constructor, otherwise FML cannot use it.

	执行包处理类
	The IMessageHandler simply requires one method, onMessage. This method is called when your packet is received, after the IMessage instance has been created and fromBytes has been called.
	Do whatever you want your packet to do in this method.
	Warning for Minecraft 1.8: As of Minecraft 1.8 onMessage is no longer called on the main thread but on the Netty-IO-Thread instead. Therefor you must not interact with the World or other "normal Minecraft-y things" in here directly. Instead you must schedule your code to be run on the main thread, which is done through the IThreadListener interface and it's addScheduledTask method. The Runnable you pass to this method will be executed on the main thread. The IThreadListener instance is Minecraft.getMinecraft() on the client and (WorldServer) ctx.getServerHandler().playerEntity.worldObj on the server. See below for examples.

	发送你的包
	The SimpleNetworkWrapper provides you with methods to send IMessage instances to various targets. They should be pretty self-explanatory (sendToServer, sendTo, etc.).
	If a vanilla packet is needed from your IMessage (for use e.g. as a TileEntity description packet) you can use the getPacketFrom method.

	数据包响应
	The IMessageHandler has a built-in response functionality. Say you send a packet from the client to the server, then you can easily just send a response directly from the onMessage method, by returning it. That is also what the 2nd type-parameter on IMessageHandler is used for, although leaving it at IMessage doesn't hurt, even if you do have a reply message.
*/
