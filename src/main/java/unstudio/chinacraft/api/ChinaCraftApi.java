package unstudio.chinacraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.remote.VersionChecker;

/**
 * 提供给其他MOD的API的类
 */
public class ChinaCraftApi {
    public static VersionChecker getVersionChecker() {
        return ChinaCraft.versionChecker;
    }

    /**
     * 判断是否穿着全套夜行衣
     * @param player 玩家
     * @return 是否穿着夜行衣
     */
    public static boolean isWearingWholeNightClothes(EntityPlayer player){
        return player.getEntityData().hasKey("chinacraft.wearingWholeNightClothes");
    }
}
