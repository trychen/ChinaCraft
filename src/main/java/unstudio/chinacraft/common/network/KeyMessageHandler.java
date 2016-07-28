package unstudio.chinacraft.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by trychen on 16/7/28.
 */
public class KeyMessageHandler implements IMessageHandler<KeyMessage, IMessage> {
    @Override
    public IMessage onMessage(KeyMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        switch(message.key){
            case 0:
                NBTTagCompound tCompound = player.getEntityData();
                if(tCompound.hasKey("nightClothesHasJumped")) {
                    if (player.motionY < 0.4 && tCompound.getInteger("nightClothesHasJumped") < 2) {
                        player.jump();
                        player.motionX *= 1.3;
                        player.motionY += 0.15;
                        player.motionZ *= 1.3;
                        player.velocityChanged = true;
                    }
                }
                break;
        }
        return null;
    }
}
