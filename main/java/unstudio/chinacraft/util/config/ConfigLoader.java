package unstudio.chinacraft.util.config;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by trychen on 15/11/15.
 */
public class ConfigLoader {
    public static Configuration mainConfig;

    public ConfigLoader(Configuration config){
        this.mainConfig = config;
        load();
    }

    public static void load(){
        mainConfig.load();
        mainConfig.save();
    }
}
