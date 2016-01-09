package unstudio.chinacraft.util.config;

import net.minecraftforge.common.config.Configuration;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trych on 2016/1/9.
 */
public class FeatureConfig {
    private Configuration c = ChinaCraft.getMainConfig();
    public boolean ItemBombDestoryBlock;
    public boolean ItemBombInRedPackerExplosion;

    public FeatureConfig() {
    }
}
