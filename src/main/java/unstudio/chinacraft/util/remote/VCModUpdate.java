package unstudio.chinacraft.util.remote;

import com.dynious.versionchecker.api.Update;

/**
 * Created by trychen on 16/7/19.
 */
public class VCModUpdate extends Update {
    public VCModUpdate(MinecraftModVersionChecker checker) {
        super(checker.getModAnno().modid());
        displayName = checker.getDisplayName();
        if (!checker.isChecked()) setErrored();
        if (!checker.isLatestVersion()) setDownloaded(true);
        else {
            updateURL = checker.getDownloadUrl();
            newVersion = checker.getLatestVersion();
            oldVersion = checker.getModAnno().version();
            changeLog = checker.getInstruction() + "\n" + checker.getChangelog();
        }
    }
}
