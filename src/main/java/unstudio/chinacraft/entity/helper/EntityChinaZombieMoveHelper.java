package unstudio.chinacraft.entity.helper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;
import unstudio.chinacraft.entity.mob.EntityChinaZombie;

public class EntityChinaZombieMoveHelper extends EntityMoveHelper {

    /** The EntityLiving that is being moved */
    private EntityChinaZombie entityChinaZombie;
    private double posX;
    private double posY;
    private double posZ;
    /** The speed at which the entity should move */
    private double speed;
    /** 表示实体是否正在运动到某一地点的途中 */
    private boolean update;
    private int tickSinceUp;
    private boolean prevUp;
    private static final String __OBFID = "CL_00001573";

    public EntityChinaZombieMoveHelper(EntityChinaZombie p_i1614_1_)
    {
        super(p_i1614_1_);
        this.entityChinaZombie = p_i1614_1_;
        this.posX = p_i1614_1_.posX;
        this.posY = p_i1614_1_.posY;
        this.posZ = p_i1614_1_.posZ;
    }
    
    @Override
    public boolean isUpdating()
    {
        return this.update;
    }

    @Override
    public double getSpeed()
    {
        return this.speed;
    }

    /**
     * Sets the speed and location to move to
     */
    @Override
    public void setMoveTo(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_)
    {
        this.posX = p_75642_1_;
        this.posY = p_75642_3_;
        this.posZ = p_75642_5_;
        this.speed = p_75642_7_;
        this.update = true;
    }
    
    @Override
    public void onUpdateMoveHelper()
    {
        this.entityChinaZombie.setMoveForward(0.0F);
        this.entityChinaZombie.jumpDelay--;
        if (this.update) {
            this.update = false;
            int i = MathHelper.floor_double(this.entityChinaZombie.boundingBox.minY + 0.5D);
            double d0 = this.posX - this.entityChinaZombie.posX;
            double d1 = this.posZ - this.entityChinaZombie.posZ;
            double d2 = this.posY - (double)i;
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (entityChinaZombie.jumpDelay > 0) {
                // 这儿放了5 ticks的时间给实体速度,是为了防止实体无法跳上一格高的方块 
                if (entityChinaZombie.jumpDelay > this.entityChinaZombie.getJumpDelay() - 5)
                    this.entityChinaZombie.setAIMoveSpeed(this.entityChinaZombie.JUMP_SPEED_FACTOR * (float)(this.speed * this.entityChinaZombie.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
            }
            else {
                // 重置跳跃间隔时间
                this.entityChinaZombie.jumpDelay = this.entityChinaZombie.getJumpDelay();
                // 将原版代码中的实体运动速度和跳跃条件修改如下
                if (d3 >= 2.500000277905201E-7D) {
                    this.entityChinaZombie.setAIMoveSpeed(this.entityChinaZombie.JUMP_SPEED_FACTOR * (float)(this.speed * this.entityChinaZombie.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
                    this.entityChinaZombie.getJumpHelper().setJumping();
                }
            }
        }
    }

    /**
     * Limits the given angle to a upper and lower limit.
     * 此方法是原EntityMoveHelper类中的方法
     * 但由于这儿暂时用不到所以Deprecate掉了
     */
    @Deprecated
    private float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_)
    {
        float f3 = MathHelper.wrapAngleTo180_float(p_75639_2_ - p_75639_1_);

        if (f3 > p_75639_3_)
        {
            f3 = p_75639_3_;
        }

        if (f3 < -p_75639_3_)
        {
            f3 = -p_75639_3_;
        }

        return p_75639_1_ + f3;
    }

}
