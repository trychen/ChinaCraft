package unstudio.chinacraft.client.nei;

import unstudio.chinacraft.client.gui.GuiBuhrimill;
import unstudio.chinacraft.common.ChinaCraft;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

/**
 * NEIAPI 入口
 */
public class NEIAPI implements IConfigureNEI {
    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new BuhrimillRecipeHandler());
        API.registerUsageHandler(new BuhrimillRecipeHandler());
        API.registerGuiOverlay(GuiBuhrimill.class, "buhrimill");
    }

    @Override
    public String getName() {
        return ChinaCraft.NAME;
    }

    @Override
    public String getVersion() {
        return ChinaCraft.VERSION;
    }

}
