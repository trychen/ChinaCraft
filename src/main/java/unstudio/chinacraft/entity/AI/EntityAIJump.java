package unstudio.chinacraft.entity.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIJump extends EntityAIBase {

    public EntityLiving entity;
    public World world;
    public int cooldown;
    public double jump;
    public boolean jumped = false;
    public int frequency;

    public EntityAIJump(EntityLiving entity, double jump, double frequency) {
        this.entity = entity;
        this.world = entity.worldObj;
        this.cooldown = 10;
        this.jump = jump;
        this.frequency = MathHelper.floor_double(1.0D / frequency);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    public void startExecuting() {
        this.world.setEntityState(this.entity, (byte) 10);
        this.entity.getNavigator().clearPathEntity();
    }

    public boolean continueExecuting() {
        return false;
    }

    public void updateTask() {
        cooldown = 10;
        if (entity.onGround) jumped = false;
        if (!jumped) {
            entity.setVelocity(0, jump, 0);
            entity.velocityChanged = true;
            jumped = true;
        }
    }
}
