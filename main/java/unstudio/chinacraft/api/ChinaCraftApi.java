package unstudio.chinacraft.api;

import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.util.VersionChecker;

/**
 * Created by trychen on 15/11/15.
 */
public class ChinaCraftApi {
    public static VersionChecker getVersionChecker(){
        return ChinaCraft.versionChecker;
    }
}
