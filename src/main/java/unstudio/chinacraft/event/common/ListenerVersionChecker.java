package unstudio.chinacraft.event.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import unstudio.chinacraft.api.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.event.jade.PlayerUseJadeEvent;


public class ListenerVersionChecker {
        @SubscribeEvent
        public void worldLoad(EntityJoinWorldEvent e){
            if (e.entity instanceof EntityPlayer) {
                EntityPlayer p = (EntityPlayer) e.entity;
                if (!ChinaCraft.haveWarnedVersionOutOfDate && p.worldObj.isRemote
                        && !ChinaCraft.versionChecker.isLatestVersion() && ChinaCraft.versionChecker.isCheckable()) {
                    ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                            ChinaCraft.versionChecker.getDownloadUrl());
                    ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
                    ChatComponentText versionWarningChatComponent =
                            new ChatComponentText(StatCollector.translateToLocal("VersionChecker.perfix"));
                    versionWarningChatComponent.setChatStyle(clickableChatStyle);
                    p.addChatMessage(versionWarningChatComponent);

                    ChatComponentText info = new ChatComponentText(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new", String.valueOf(ChinaCraft.versionChecker.getLatestVersion())).replaceAll("!old", String.valueOf(ChinaCraft.OutPutVERSION)));
                    info.setChatStyle(clickableChatStyle);
                    p.addChatMessage(info);
                    System.out.println(StatCollector.translateToLocal("VersionChecker.perfix"));
                    System.out.println(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new", String.valueOf(ChinaCraft.versionChecker.getLatestVersion())).replaceAll("!old", String.valueOf(ChinaCraft.OutPutVERSION)));
                    System.out.println("[ChinaCraft]Download Url:" + ChinaCraft.versionChecker.getDownloadUrl());
                    ChinaCraft.haveWarnedVersionOutOfDate = true;
                }
            }
        }
}
