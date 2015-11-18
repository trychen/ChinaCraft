package unstudio.HugeSword;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class Listener {
        @SubscribeEvent
        public void worldLoad(EntityJoinWorldEvent e){
            if (e.entity instanceof EntityPlayer) {
                EntityPlayer p = (EntityPlayer) e.entity;
                if (!HugeSword.haveWarnedVersionOutOfDate && p.worldObj.isRemote
                        && !HugeSword.versionChecker.getisLatestVersion()) {
                    ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                            HugeSword.versionChecker.getDownloadUrl());
                    ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
                    ChatComponentText versionWarningChatComponent =
                            new ChatComponentText(StatCollector.translateToLocal("VersionChecker.perfix"));
                    versionWarningChatComponent.setChatStyle(clickableChatStyle);
                    p.addChatMessage(versionWarningChatComponent);

                    ChatComponentText info = new ChatComponentText(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new", String.valueOf(HugeSword.versionChecker.getLatestVersion())).replaceAll("!old", String.valueOf(HugeSword.OutPutVERSION)));
                    info.setChatStyle(clickableChatStyle);
                    p.addChatMessage(info);
                    System.out.println(StatCollector.translateToLocal("VersionChecker.perfix"));
                    System.out.println(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new", String.valueOf(HugeSword.versionChecker.getLatestVersion())).replaceAll("!old", String.valueOf(HugeSword.OutPutVERSION)));
                    System.out.println("[ChinaCraft]Download Url:" + HugeSword.versionChecker.getDownloadUrl());
                    HugeSword.haveWarnedVersionOutOfDate = true;
                }
            }
        }
}
