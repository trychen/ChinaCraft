package unstudio.chinacraft.api;

import cpw.mods.fml.common.Mod;

/**
 * Created by trychen on 15/11/15.
 */
@Mod(modid= ChinaCraftApi.MODID, name=ChinaCraftApi.NAME, version=ChinaCraftApi.VERSION)
public class ChinaCraftApi {
    public static final String MODID = "ChinaCraft|API";
    public static final String NAME = "ChinaCraft API";
    public static final String VERSION = "172";
    public static Object getVersionChecker(){
        try {
            return Class.forName("unstudio.chinacraft.common.ChinaCraft").getMethod("getVersionChecker",null).invoke(new ChinaCraftApi(),null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
