package unstudio.chinacraft.item.combat;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.item.combat.models.ModelChinaCrown;
import unstudio.chinacraft.item.combat.models.ModelNightClothes;

public class ModelArmorRegister {
    public static void init() {
        ChinaCraft.chinaCrown.setArmorModel(new ModelChinaCrown(0.5f));

        ModelNightClothes modelNightClothesLeg = new ModelNightClothes(0.5F);
        ModelNightClothes modelNightClothes = new ModelNightClothes(1F);
        ChinaCraft.nightClothes[0].setArmorModel(modelNightClothes);
        ChinaCraft.nightClothes[1].setArmorModel(modelNightClothes);
        ChinaCraft.nightClothes[2].setArmorModel(modelNightClothesLeg);
        ChinaCraft.nightClothes[3].setArmorModel(modelNightClothes);
    }
}
