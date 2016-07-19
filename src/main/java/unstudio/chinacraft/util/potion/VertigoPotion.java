package unstudio.chinacraft.util.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

/**
 * Created by trychen on 16/6/9.
 */
public class VertigoPotion extends Potion{
    protected VertigoPotion(ResourceLocation location, boolean badEffect, int potionColor) {
        super(location, badEffect, potionColor);
    }

    @Override
    protected Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
        super.setIconIndex(p_76399_1_, p_76399_2_);
        return this;
    }
}
