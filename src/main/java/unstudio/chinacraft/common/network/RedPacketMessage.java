package unstudio.chinacraft.common.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RedPacketMessage implements IMessage { // 包类

    public ItemStack itemstack;

    public RedPacketMessage() {}

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
        (new PacketBuffer(buf)).writeItemStackToBuffer(this.itemstack);
    }
}
