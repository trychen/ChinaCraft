package unstudio.chinacraft.entity.animal;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * 黑狗
 */
public class EntityBlackDog extends EntityWolf {

    public EntityBlackDog(World p_i1696_1_) {
        super(p_i1696_1_);
    }

    @Override
    public EntityWolf createChild(EntityAgeable p_90011_1_) {
        EntityBlackDog entitywolf = new EntityBlackDog(this.worldObj);
        String s = this.getEntityString();

        if (s != null && s.trim().length() > 0) {
//            TODO WTF entitywolf.func_152115_b(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    @Override
    public void onDeath(DamageSource p_70645_1_) {
        if (p_70645_1_.getSourceOfDamage() != null && p_70645_1_.getSourceOfDamage() instanceof EntityPlayer){
            ((EntityPlayer) p_70645_1_.getSourceOfDamage()).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:wither"), ChinaCraft.rand.nextInt(70)+30));
        }
        super.onDeath(p_70645_1_);
    }
}
