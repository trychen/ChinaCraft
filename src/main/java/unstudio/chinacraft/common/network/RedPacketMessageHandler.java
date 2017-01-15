package unstudio.chinacraft.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RedPacketMessageHandler implements IMessageHandler<RedPacketMessage, IMessage> { // 包处理类
    @Override
    public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        ItemStack itemStack = player.inventory.getCurrentItem();

        if (itemStack == null || itemStack.getItem() != ChinaCraft.redPacket)return null;
        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

        itemStack = itemStack.copy();
        ItemStack itemStack2 = itemStack.copy();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("wish",new NBTTagString(message.wish));
        nbt.setTag("sendee",new NBTTagString(message.sendee));
        itemStack.setTagInfo("redpacket", nbt);
        NBTTagCompound nbt2 = (NBTTagCompound)nbt.copy();
        nbt2.setTag("sender",new NBTTagString(message.sender));
        itemStack2.setTagInfo("redpacket", nbt2);

        String sendee = message.sendee;
        if (sendee == null || sendee.isEmpty() || !message.issend || sendee.equalsIgnoreCase(player.getDisplayName())) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
            return null;
        }

        EntityPlayer sendeePlayer = getPlayer(sendee);
        if (sendeePlayer == null) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
            player.addChatMessage(new ChatComponentText(StatCollector
                    .translateToLocal("redpacket.not_found_player").replaceAll("%sendee%", sendee)));
            return null;
        }

        if (sendeePlayer.inventory.addItemStackToInventory(itemStack2)) {
            player.addChatMessage(new ChatComponentText(
                    StatCollector.translateToLocal("redpacket.success").replaceAll("%sendee%", sendee)));
            sendeePlayer.addChatMessage(
                    new ChatComponentText(StatCollector.translateToLocal("redpacket.received")));
            return null;
        } else {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
            player.addChatMessage(new ChatComponentText(
                    StatCollector.translateToLocal("redpacket.backpack_full").replaceAll("%sendee%", sendee)));
            return null;
        }
    }

    private EntityPlayer getPlayer(String name) {
        for (WorldServer world : MinecraftServer.getServer().worldServers) {
            EntityPlayer player = world.getPlayerEntityByName(name);
            if (player != null) {
                return player;
            }
        }
        return null;
    }
}
