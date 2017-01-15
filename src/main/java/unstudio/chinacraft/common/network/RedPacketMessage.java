package unstudio.chinacraft.common.network;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class RedPacketMessage implements IMessage { // 包类

    public String sender;
    public String wish;
    public String sendee;
    public boolean issend;

    public RedPacketMessage() {}

    public RedPacketMessage(String sender,String wish,String sendee,boolean issend) {
        this.sender=sender;
        this.wish=wish;
        this.sendee=sendee;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            PacketBuffer pb= new PacketBuffer(buf);
            sender=pb.readStringFromBuffer(pb.readInt());
            wish=pb.readStringFromBuffer(pb.readInt());
            sendee=pb.readStringFromBuffer(pb.readInt());
            issend=pb.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        try {
            PacketBuffer pb= new PacketBuffer(buf);
            pb.writeInt(sender==null?0:sender.length());
            pb.writeStringToBuffer(sender);
            pb.writeInt(wish==null?0:wish.length());
            pb.writeStringToBuffer(wish);
            pb.writeInt(sendee==null?0:sendee.length());
            pb.writeStringToBuffer(sendee);
            pb.writeBoolean(issend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
