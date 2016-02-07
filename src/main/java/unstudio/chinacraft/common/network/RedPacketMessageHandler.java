package unstudio.chinacraft.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;

import unstudio.chinacraft.api.common.network.RedPacketMessage;
import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RedPacketMessageHandler implements IMessageHandler<RedPacketMessage, IMessage> { // 包处理类
    @Override
    public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        ItemStack itemstack1 = player.inventory.getCurrentItem();
        if (itemstack1 == null) {
            return null;
        }

        NBTTagCompound nbt = message.itemstack.getTagCompound().getCompoundTag("Redpacket");
        if (itemstack1.getItem() == ChinaCraft.redPacket && itemstack1.getItem() == itemstack1.getItem()) {
            itemstack1.setTagInfo("Redpacket", nbt);
            String sendee = nbt.getString("Sendee");
            if (sendee != null && sendee.length() != 0 && sendee.equalsIgnoreCase(player.getDisplayName())) {
                EntityPlayer sendeePlayer = getPlayer(sendee);
                if (sendeePlayer == null) {
                    player.addChatMessage(new ChatComponentText(StatCollector
                            .translateToLocal("redpacket.not_found_player").replaceAll("%sendee%", sendee)));
                    return null;
                }
                if (sendeePlayer.inventory.addItemStackToInventory(itemstack1)) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    player.addChatMessage(new ChatComponentText(
                            StatCollector.translateToLocal("redpacket.success").replaceAll("%sendee%", sendee)));
                    sendeePlayer.addChatMessage(
                            new ChatComponentText(StatCollector.translateToLocal("redpacket.received")));
                    return null;
                } else {
                    player.addChatMessage(new ChatComponentText(
                            StatCollector.translateToLocal("redpacket.backpack_full").replaceAll("%sendee%", sendee)));
                    return null;
                }
            }
        }
        return null;
    }

    private EntityPlayer getPlayer(String name) {
        // ServerConfigurationManager manager =
        // MinecraftServer.getServer().getConfigurationManager();
        // EntityPlayer player = manager.func_152612_a(name);
        for (WorldServer world : MinecraftServer.getServer().worldServers) {
            EntityPlayer player = world.getPlayerEntityByName(name);
            if (player != null) {
                return player;
            }
        }
        return null;
    }
}
