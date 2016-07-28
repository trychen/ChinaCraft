package unstudio.sinocraft.client.nei;

import unstudio.sinocraft.client.gui.GuiBuhrimill;
import unstudio.sinocraft.common.SinoCraft;
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
        return SinoCraft.NAME;
    }

    @Override
    public String getVersion() {
        return SinoCraft.VERSION;
    }

}
