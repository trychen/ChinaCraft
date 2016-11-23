package unstudio.chinacraft.entity.mob;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.helper.EntityChinaZombieMoveHelper;

public class EntityChinaZombie extends EntityZombie {
    
    public int jumpDelay;
    private EntityMoveHelper entityMoveHelper;
    public static final float JUMP_SPEED_FACTOR = 2.0F;
    public static final float JUMP_MOTIONY_OFFSET = 0.05F;
    
    public EntityChinaZombie(World var1) {
        super(var1);
        this.entityMoveHelper = new EntityChinaZombieMoveHelper(this);
        this.setSize(0.6f, 1.9f);
    }  
    
    // 使用自定义的EntityMoveHelper
    @Override
    public EntityMoveHelper getMoveHelper() {
        return this.entityMoveHelper;
    }
    
    @Override
    protected void updateAITasks() {
        ++this.entityAge;
        this.worldObj.theProfiler.startSection("checkDespawn");
        this.despawnEntity();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("sensing");
        this.getEntitySenses().clearSensingCache();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("targetSelector");
        this.targetTasks.onUpdateTasks();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("goalSelector");
        this.tasks.onUpdateTasks();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("navigation");
        this.getNavigator().onUpdateNavigation();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("mob tick");
        this.updateAITick();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("controls");
        this.worldObj.theProfiler.startSection("move");
        // 使用自定义的EntityMoveHelper
        this.entityMoveHelper.onUpdateMoveHelper();
        this.worldObj.theProfiler.endStartSection("look");
        this.getLookHelper().onUpdateLook();
        this.worldObj.theProfiler.endStartSection("jump");
        this.getJumpHelper().doJump();
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.endSection();
        // 强制让实体对准朝向攻击目标
        // TODO: 这样会让实体无法绕过障碍物，需改
        //       应加入判定机制，判定只有在实体看不见攻击目标D时才这样调整角度
        //       且在看不见攻击目标时，实体应该按照PathEntity移动而不是正前方
        //       这需要利用EntityChinaZombieMoveHelper中被Deprecated掉的方法
        if (this.getAttackTarget() != null)
            this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
    }

    @Override
    protected void jump() {
        super.jump();
        // 由于清朝僵尸模型比较大，故给予额外的Y轴位移，以防它在跳跃后卡入地中
        this.motionY += JUMP_MOTIONY_OFFSET;
    }
    
    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int random = this.rand.nextInt(15) + par2;
        if (random == 0) {
            dropItem(ChinaCraft.smfSuper, 1);
            dropItem(Items.rotten_flesh, 1);
        } else {
            dropItem(Items.rotten_flesh, this.rand.nextInt(1) + 1);
        }
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
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
    
    /** 
     * 两次跳跃间间隔的tick数 
     */
    public int getJumpDelay() {
        return 40;
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.7D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }
}
