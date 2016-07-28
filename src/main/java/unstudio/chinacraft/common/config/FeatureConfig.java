package unstudio.chinacraft.common.config;

import net.minecraftforge.common.config.Configuration;

import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trych on 2016/1/9.
 */
public class FeatureConfig {

    public static boolean EnableUpdate;
    public static boolean ItemBombDestoryBlock;
    public static boolean ItemBombInRedPackerExplosion;
    public static boolean EnableDoubleJump;

    private Configuration c = ChinaCraft.getMainConfig();

    public FeatureConfig() {
        EnableUpdate = c.getBoolean("EnableUpdate","Common",true,"If Open Auto Version Checker");
        ItemBombDestoryBlock = c.getBoolean("ItemBombDestoryBlock","Bomb",true,"Bomb Will Destory Block If True");
        ItemBombInRedPackerExplosion = c.getBoolean("ItemBombInRedPackerExplosion","Bomb",true,"While The Red Pakcer Witch Include a Bomb Was Opened , It Will Explose If True");
        EnableDoubleJump = c.getBoolean("EnableDoubleJump","Common",true,"If enable double jump of night clothes");
    }
}
