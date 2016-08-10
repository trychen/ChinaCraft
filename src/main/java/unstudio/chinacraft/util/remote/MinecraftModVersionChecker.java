package unstudio.chinacraft.util.remote;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInterModComms;
import org.apache.logging.log4j.Logger;

/**
 * Created by trychen on 16/7/19.
 */
public class MinecraftModVersionChecker extends VersionChecker{

    private Class mod;
    private String displayName;

    public MinecraftModVersionChecker(Class mod, String displayName, int projectID, Logger log, boolean sanpShot) {
        super(((Mod) mod.getAnnotation(Mod.class)).version(), projectID, log, sanpShot);
        this.mod = mod;
        this.displayName = displayName;
    }

    public Mod getModAnno() {
        if (mod.isAnnotationPresent(Mod.class)){
            return (Mod) mod.getAnnotation(Mod.class);
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void findNewVersion() {
        getLog().info("Found New version");
        if (Loader.isModLoaded("VersionChecker")) {
            //向VersionChecker发送版本更新信息
            FMLInterModComms.sendRuntimeMessage(getModAnno().modid(), "VersionChecker", "addUpdate", new VCModUpdate(this).toJson());
        }
    }

    @Override
    public void isNewestVersion() {
        getLog().info("You are running latest version " + getModAnno().version());
    }
}
