package unstudio.chinacraft.util;

import com.dynious.versionchecker.api.Update;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trych on 2016/2/27.
 */
public class CCUpdate extends Update{
    public CCUpdate() {
        super(ChinaCraft.MODID);
        displayName = "ChinaCraft 华夏文明";
        if (!ChinaCraft.versionChecker.isCheckable()) setErrored();
        if (!ChinaCraft.versionChecker.isLatestVersion()) setDownloaded(true);
        else {
            updateURL = ChinaCraft.versionChecker.getDownloadUrl();
            newVersion = String.valueOf(ChinaCraft.versionChecker.getLatestVersion());
            oldVersion = String.valueOf(ChinaCraft.VERSION);
            changeLog = String.valueOf(ChinaCraft.versionChecker.getNewVersionInfo());
        }
    }
}
