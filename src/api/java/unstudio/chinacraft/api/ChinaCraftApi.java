package unstudio.chinacraft.api;

/**
 * Created by trychen on 15/11/15.
 */
public class ChinaCraftApi {
    public static Object getVersionChecker(){
        try {
            return Class.forName("unstudio.chinacraft.common.ChinaCraft").getMethod("getVersionChecker",null).invoke(new ChinaCraftApi(),null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
