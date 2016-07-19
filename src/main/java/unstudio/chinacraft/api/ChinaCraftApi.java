package unstudio.chinacraft.api;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.checker.VersionChecker;

/**
 * 提供给其他MOD的API的类
 */
public class ChinaCraftApi {
    public static VersionChecker getVersionChecker() {
        return ChinaCraft.versionChecker;
    }
}
