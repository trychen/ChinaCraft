package unstudio.chinacraft.util.remote;

import com.dynious.versionchecker.api.Update;

/**
 * Created by trychen on 16/7/19.
 */
public class VCModUpdate extends Update {
    public VCModUpdate(MinecraftModVersionChecker checker) {
        super(checker.getModAnno().modid());
        displayName = checker.getDisplayName();
        updateURL = checker.getDownloadUrl();
        newVersion = checker.getLatestVersion();
        oldVersion = checker.getModAnno().version().replace('-', ' ');
        changeLog = checker.getInstruction() + "\n" + checker.getChangelog().replaceAll("\r","");
    }
}
