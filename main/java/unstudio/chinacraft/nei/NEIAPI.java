package unstudio.chinacraft.nei;

import codechicken.nei.api.API;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.inventory.GuiBuhrimill;

public class NEIAPI implements codechicken.nei.api.IConfigureNEI{

	@Override
	public void loadConfig() {
//        API.registerRecipeHandler(new BuhrimillRecipeHandler());
//        API.registerUsageHandler(new BuhrimillRecipeHandler());
//        API.registerGuiOverlay(GuiBuhrimill.class, "buhrimill");
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
