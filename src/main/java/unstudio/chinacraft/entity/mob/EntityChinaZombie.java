package unstudio.chinacraft.entity.mob;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.AI.EntityAIJump;

public class EntityChinaZombie extends EntityZombie {
    public EntityChinaZombie(World var1) {
        super(var1);
        // this.setSize(0.9f, 1.9f);
        this.tasks.addTask(0, new EntityAIJump(this,0.8D, 0.05D));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int random = this.rand.nextInt(30) + par2;
        if (random == 0) {
            dropItem(ChinaCraft.smfSuper, 1);
            dropItem(Items.rotten_flesh, 1);
        } else {
            dropItem(Items.rotten_flesh, this.rand.nextInt(1) + 1);
        }
    }

    public EntityMob createChild(EntityAgeable p_90011_1_) {
        EntityChinaZombie entityzombie = new EntityChinaZombie(this.worldObj);
        String s = this.func_146067_o(arrowHitTimer);

        if (s != null && s.trim().length() > 0) {
            entityzombie.func_142017_o(getAge());
            entityzombie.canPickUpLoot();
        }

        return entityzombie;
    }

    @Override
    public void onUpdate() {
        if (targetTasks != null)
            new EntityJumpHelper(this).doJump();
        if (isJumping == true) {
            motionY *= 1.6;
            motionX *= 1.2D;
            motionZ *= 1.2D;
        }
        super.onUpdate();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.28000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.7D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }
}
