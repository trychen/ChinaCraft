package unstudio.chinacraft.common.config;

import net.minecraftforge.common.config.Configuration;

import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trych on 2016/1/9.
 */
public class FeatureConfig {

    public static boolean EnableUpdate;
    public static boolean EnableSanpShot;
    public static boolean ItemBombDestoryBlock;
    public static boolean ItemBombInRedPackerExplosion;
    public static boolean EnableDoubleJump;
    public static boolean enableAdvancedSericulture;

    private Configuration c = ChinaCraft.getMainConfig();

    public FeatureConfig() {
        EnableUpdate = c.getBoolean("EnableUpdate","Common",true,"If Open Auto Version Checker");
        EnableSanpShot = c.getBoolean("EnableSanpShot","Common",true,"If receive SanpShot Version");
        ItemBombDestoryBlock = c.getBoolean("ItemBombDestoryBlock","Bomb",true,"Bomb Will Destory Block If True");
        ItemBombInRedPackerExplosion = c.getBoolean("ItemBombInRedPackerExplosion","Bomb",true,"While The Red Pakcer Witch Include a Bomb Was Opened , It Will Explose If True");
        EnableDoubleJump = c.getBoolean("EnableDoubleJump","Common",true,"If enable double jump of night clothes");
        enableAdvancedSericulture = c.getBoolean("EnableAdvancedSericulture", "Sericulture", true, "Set this to true will enable the advanced Sericulture, which means that the silkworms will have extra attributes, such like the productivity, speed and fertility. This is more complicated but is also more interesting to play with.");
    }
}
