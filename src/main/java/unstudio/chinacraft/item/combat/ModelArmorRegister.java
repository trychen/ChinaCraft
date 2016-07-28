package unstudio.sinocraft.item.combat;

import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.item.combat.models.ModelChinaCrown;
import unstudio.sinocraft.item.combat.models.ModelNightClothes;

public class ModelArmorRegister {
    public static void init() {
        SinoCraft.chinaCrown.setArmorModel(new ModelChinaCrown(0.5f));

        ModelNightClothes modelNightClothesLeg = new ModelNightClothes(0.5F);
        ModelNightClothes modelNightClothes = new ModelNightClothes(1F);
        SinoCraft.nightClothes[0].setArmorModel(modelNightClothes);
        SinoCraft.nightClothes[1].setArmorModel(modelNightClothes);
        SinoCraft.nightClothes[2].setArmorModel(modelNightClothesLeg);
        SinoCraft.nightClothes[3].setArmorModel(modelNightClothes);
    }
}
