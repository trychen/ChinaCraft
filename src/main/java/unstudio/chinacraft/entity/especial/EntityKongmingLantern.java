package unstudio.chinacraft.entity.especial;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 孔明灯
 */
public class EntityKongmingLantern extends Entity {

    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;

    public EntityKongmingLantern(World p_i1582_1_) {
        super(p_i1582_1_);
        preventEntitySpawning = true;
        setSize(1.0F, 1.5F);
    }

    // 返回碰撞箱
    @Override
    public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
        return p_70114_1_.getEntityBoundingBox();
    }

    // 如果为true,则该实体可以被推动
    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        this.velocityX = this.motionX = p_70016_1_;
        this.velocityY = this.motionY = p_70016_3_;
        this.velocityZ = this.motionZ = p_70016_5_;
    }

    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void entityInit() {
        // TODO 自动生成的方法存根

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        // TODO 自动生成的方法存根

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        // TODO 自动生成的方法存根

    }

}
