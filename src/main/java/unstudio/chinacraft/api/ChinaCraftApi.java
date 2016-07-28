package unstudio.sinocraft.api;

import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.util.remote.VersionChecker;

/**
 * 提供给其他MOD的API的类
 */
public class ChinaCraftApi {
    public static VersionChecker getVersionChecker() {
        return SinoCraft.versionChecker;
    }
}
