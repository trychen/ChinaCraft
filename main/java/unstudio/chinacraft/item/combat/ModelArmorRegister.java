package unstudio.chinacraft.item.combat;

import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.item.combat.models.ModelChinaCrown;
import unstudio.chinacraft.item.combat.models.ModelNightClothes;

/**
 * Created by trychen on 15/11/18.
 */
public class ModelArmorRegister {
    public static void init(){
        ModelChinaCrown modelChinaCrown;
        modelChinaCrown = new ModelChinaCrown(0.5f);
        ChinaCraft.chinaCrown.setArmorModel(modelChinaCrown);

        ModelNightClothes modelNightClothes= new ModelNightClothes(1F);;
        ModelNightClothes modelNightClothesleg= new ModelNightClothes(0.5F);;
        ChinaCraft.nightClothesHead.setArmorModel(modelNightClothes);
        ChinaCraft.nightClothesBody.setArmorModel(modelNightClothes);
        ChinaCraft.nightClothesLeg.setArmorModel(modelNightClothesleg);
        ChinaCraft.nightClothesShoe.setArmorModel(modelNightClothes);
    }
}
