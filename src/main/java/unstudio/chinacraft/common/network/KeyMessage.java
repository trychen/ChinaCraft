package unstudio.chinacraft.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by trychen on 16/7/28.
 */
public class KeyMessage implements IMessage{
    public static final int KEY_DOUBLE_JUMP = 0;
    public static final int KEY_ATTACK = 1;
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
