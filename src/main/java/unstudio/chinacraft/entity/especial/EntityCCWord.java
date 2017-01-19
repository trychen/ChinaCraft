package unstudio.chinacraft.entity.especial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.world.World;

/**
 * Created by trychen on 17/1/19.
 */
public class EntityCCWord extends EntityHanging {
    public EntityCCWord(World p_i1588_1_) {
        super(p_i1588_1_);
    }

    public EntityCCWord(World p_i1589_1_, int p_i1589_2_, int p_i1589_3_, int p_i1589_4_, int p_i1589_5_) {
        super(p_i1589_1_, p_i1589_2_, p_i1589_3_, p_i1589_4_, p_i1589_5_);
        this.setDirection(p_i1589_5_);
    }

    @Override
    public int getWidthPixels() {
        return 10;
    }

    @Override
    public int getHeightPixels() {
        return 10;
    }

    /**
     * Called when this entity is broken. Entity parameter may be null.
     *
     * @param entity
     */
    @Override
    public void onBroken(Entity entity) {

    }
}
