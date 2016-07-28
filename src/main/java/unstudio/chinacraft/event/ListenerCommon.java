package unstudio.sinocraft.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import unstudio.sinocraft.common.SinoCraft;

/**
 * Created by trychen on 16/7/12.
 */
public class ListenerCommon {
    /**
     * 在玩家首次加入世界时,检查是否有新版本
     * @param e 实体加入世界
     */
    @SubscribeEvent
    public void VersionChecker(EntityJoinWorldEvent e) {
        //判断加入的是否为玩家
        if (e.entity instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.entity;
            // 判断是否已经提示过、是否为服务端、是否已经检查过、是否已经是最新版本
            if (!SinoCraft.haveWarnedVersionOutOfDate && p.worldObj.isRemote
                    && !SinoCraft.versionChecker.isLatestVersion() && SinoCraft.versionChecker.isChecked()) {
                ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                        SinoCraft.versionChecker.getDownloadUrl());
                ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
                ChatComponentText versionWarningChatComponent = new ChatComponentText("[SinoCraft] " +
                        StatCollector.translateToLocal("VersionChecker.perfix"));
                versionWarningChatComponent.setChatStyle(clickableChatStyle);
                p.addChatMessage(versionWarningChatComponent);

                ChatComponentText info = new ChatComponentText("[SinoCraft] " + StatCollector.translateToLocal("VersionChecker.version")
                        .replaceAll("!new", String.valueOf(SinoCraft.versionChecker.getLatestVersion()))
                        .replaceAll("!old", String.valueOf(SinoCraft.VERSION.replace('-',' '))));
                info.setChatStyle(clickableChatStyle);
                p.addChatMessage(info);
                //一下信息
                SinoCraft.log.info(StatCollector.translateToLocal("VersionChecker.perfix"));
                SinoCraft.log.info(StatCollector.translateToLocal("VersionChecker.version")
                        .replaceAll("!new", String.valueOf(SinoCraft.versionChecker.getLatestVersion()))
                        .replaceAll("!old", String.valueOf(SinoCraft.VERSION)));
                SinoCraft.log.info("Download Url:" + SinoCraft.versionChecker.getDownloadUrl());
                SinoCraft.haveWarnedVersionOutOfDate = true;
            }
        }
    }
}
