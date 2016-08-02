package unstudio.chinacraft.entity.animal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * The Chinese Animal - Panda. Created by trychen on 15/12/5.
 */
public class EntityPanda extends EntityAnimal implements IAnimals {

    public EntityPanda(World world) {
        super(world);
        this.setSize(0.9F, 1.3F);
//        TODO this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("Angry", this.isAngry());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.setAngry(p_70037_1_.getBoolean("Angry"));
    }

    /**
     * Determines whether this wolf is angry or not.
     */
    public boolean isAngry() {
//        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
        return false;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean p_70916_1_) {
//        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//
//        if (p_70916_1_) {
//            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
//        } else {
//            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
//        }
    }

//    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return new EntityPanda(this.worldObj);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
//    @Override
    protected String getLivingSound() {
        return "mob.cow.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected SoundEvent getHurtSound() {
        return new SoundEvent(new ResourceLocation("mob.cow.hurt"));
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected SoundEvent getDeathSound() {
        return new SoundEvent(new ResourceLocation("mob.cow.hurt"));
    }

    @Override
    protected Item getDropItem() {
        return Items.LEATHER;
    }

//   TODO  @Override
    public boolean interact(EntityPlayer entityPlayer) {
        ItemStack itemstack = entityPlayer.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == ChinaCraft.itemBamboo
                && !entityPlayer.capabilities.isCreativeMode) {
            if (itemstack.stackSize-- == 1) {
                entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem,
                        new ItemStack(Items.MILK_BUCKET));
            } else if (!entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET))) {
                entityPlayer.dropItem(new ItemStack(Items.MILK_BUCKET, 1, 0), false);
            }

            return true;
        } else {
//            return super.interact(entityPlayer);
            return true;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isEntityInvulnerable(p_70097_1_)) {
            return false;
        } else {
            Entity entity = p_70097_1_.getEntity();

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3f);
    }
}
