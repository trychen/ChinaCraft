package unstudio.chinacraft.entity.mob;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

public class EntityChinaZombie extends EntityZombie {
    public EntityChinaZombie(World var1) {
        super(var1);
        // this.setSize(0.9f, 1.9f);
//        this.tasks.addTask(0, new EntityAIJump(this,0.8D, 0.05D));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int random = this.rand.nextInt(15) + par2;
        if (random == 0) {
            dropItem(ChinaCraft.smfSuper, 1);
            dropItem(Items.ROTTEN_FLESH, 1);
        } else {
            dropItem(Items.ROTTEN_FLESH, this.rand.nextInt(1) + 1);
        }
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    public EntityMob createChild(EntityAgeable p_90011_1_) {
        EntityChinaZombie entityzombie = new EntityChinaZombie(this.worldObj);
//        String s = this.func_146067_o(arrowHitTimer);

//        if (s != null && s.trim().length() > 0) {
//            entityzombie.func_142017_o(getAge());
//            entityzombie.canPickUpLoot();
//        }

        return entityzombie;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.isJumping && this.isAirBorne){
            this.jump();
            motionY += 0.6D;
            this.isJumping = true;
        }
        if (onGround) isJumping = false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.7D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }
}
