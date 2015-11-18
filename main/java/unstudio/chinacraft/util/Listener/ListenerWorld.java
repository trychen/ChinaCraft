package unstudio.chinacraft.util.Listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;


public class ListenerWorld {
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(TickEvent.PlayerTickEvent event)
    {

        if (!ChinaCraft.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote
                && !ChinaCraft.versionChecker.getisLatestVersion())
        {
            ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                    ChinaCraft.versionChecker.getDownloadUrl());
            ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
            ChatComponentText versionWarningChatComponent =
                    new ChatComponentText(StatCollector.translateToLocal("VersionChecker.perfix"));
            versionWarningChatComponent.setChatStyle(clickableChatStyle);
            event.player.addChatMessage(versionWarningChatComponent);

            ChatComponentText info = new ChatComponentText(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new",String.valueOf(ChinaCraft.versionChecker.getLatestVersion())).replaceAll("!old",String.valueOf(ChinaCraft.OutPutVERSION)));
            info.setChatStyle(clickableChatStyle);
            event.player.addChatMessage(info);
            System.out.println(StatCollector.translateToLocal("VersionChecker.perfix"));
            System.out.println(StatCollector.translateToLocal("VersionChecker.version").replaceAll("!new",String.valueOf(ChinaCraft.versionChecker.getLatestVersion())).replaceAll("!old",String.valueOf(ChinaCraft.OutPutVERSION)));
            System.out.println("[ChinaCraft]Download Url:"+ChinaCraft.versionChecker.getDownloadUrl());
            ChinaCraft.haveWarnedVersionOutOfDate = true;
        }

    }
}
