package unstudio.chinacraft.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by trychen on 16/7/28.
 */
public class KeyMessage implements IMessage {
    public int key;

    public KeyMessage(int key) {
        this.key = key;
    }

    public KeyMessage() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        key = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(key);
    }
}
