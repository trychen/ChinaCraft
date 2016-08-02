package unstudio.chinacraft.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BaseMessage implements IMessage { // 包类

    private String text;

    public BaseMessage(String text) {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in
                                                 // general for writing more
                                                 // complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<BaseMessage, IMessage> { // 包处理类
        @Override
        public IMessage onMessage(BaseMessage message, MessageContext ctx) {
            System.out.println(String.format("Received %s from %s", message.text,
                    ctx.getServerHandler().playerEntity.getDisplayName()));
            return null;
        }
    }
}
