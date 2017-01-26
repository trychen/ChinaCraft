package unstudio.chinacraft.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.util.FestivalHelper;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by trychen on 16/7/12.
 */
public class ListenerCommon {
    /**
     * 在玩家首次加入世界时,检查是否有新版本
     * @param e 实体加入世界
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void VersionChecker(EntityJoinWorldEvent e) {
        //判断加入的是否为玩家
        if (e.entity instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.entity;
            // 判断是否开启更新提示、已经提示过、是否为服务端、是否已经检查过、是否已经是最新版本
            if (FeatureConfig.EnableUpdate&&!ChinaCraft.haveWarnedVersionOutOfDate && p.worldObj.isRemote
                    && !ChinaCraft.versionChecker.isLatestVersion() && ChinaCraft.versionChecker.isChecked() && !ChinaCraft.VersionCheckerIsLoad) {
                ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL,
                        ChinaCraft.versionChecker.getDownloadUrl());
                ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
                ChatComponentText versionWarningChatComponent = new ChatComponentText("[ChinaCraft] " +
                        StatCollector.translateToLocal("VersionChecker.perfix"));
                versionWarningChatComponent.setChatStyle(clickableChatStyle);
                p.addChatMessage(versionWarningChatComponent);

                ChatComponentText info = new ChatComponentText("[ChinaCraft] " + StatCollector.translateToLocal("VersionChecker.version")
                        .replaceAll("!new", String.valueOf(ChinaCraft.versionChecker.getLatestVersion()))
                        .replaceAll("!old", String.valueOf(ChinaCraft.VERSION.replace('-',' '))));
                info.setChatStyle(clickableChatStyle);
                p.addChatMessage(info);
                //一下信息
                ChinaCraft.log.info(StatCollector.translateToLocal("VersionChecker.perfix"));
                ChinaCraft.log.info(StatCollector.translateToLocal("VersionChecker.version")
                        .replaceAll("!new", String.valueOf(ChinaCraft.versionChecker.getLatestVersion()))
                        .replaceAll("!old", String.valueOf(ChinaCraft.VERSION)));
                ChinaCraft.log.info("Download Url:" + ChinaCraft.versionChecker.getDownloadUrl());
                ChinaCraft.haveWarnedVersionOutOfDate = true;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onSpring(GuiScreenEvent.InitGuiEvent event){
        if (event.gui instanceof GuiMainMenu){
            if (FestivalHelper.getFestival() != FestivalHelper.Festival.Spring) return;
            Field text;
            try {
                text = GuiMainMenu.class.getDeclaredField("field_73975_c");
            } catch (NoSuchFieldException e) {
                try {
                    text = GuiMainMenu.class.getDeclaredField("splashText");
                } catch (NoSuchFieldException e1) {
                    return;
                }
            }
            text.setAccessible(true);
            try {
                text.set(event.gui,StatCollector.translateToLocal("splashText.spring." + (new Random().nextInt(3) + 1)));
            } catch (IllegalAccessException e) {
            }
        }
    }
}
