package unstudio.chinacraft.entity.especial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

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
        return 16;
    }

    @Override
    public int getHeightPixels() {
        return 16;
    }

    public void onBroken(Entity p_110128_1_) {
        if (p_110128_1_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) p_110128_1_;

            if (entityplayer.capabilities.isCreativeMode) {
                return;
            }
        }

        this.entityDropItem(new ItemStack(ChinaCraft.wordFu), 0.0F);
    }

    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double p_70112_1_)
    {
        double d1 = 16.0D;
        d1 *= 64.0D * this.renderDistanceWeight;
        return p_70112_1_ < d1 * d1;
    }
}
