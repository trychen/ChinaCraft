package unstudio.chinacraft.entity.projectile;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityProjectile extends EntityThrowable {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    protected boolean inGround = false;
    private int inData = 0;
    public int throwableShake = 0;
    public int arrowShake = 0;
    public boolean canBePickedUp = false;
    public boolean onlyCanBePickedUpByThrower = false;
    public boolean destroyedOnEntityHit = true;
    private EntityLivingBase thrower;
    public EntityItem entityitem;
    private String throwerName = null;
    private int ticksInGround;
    public int ticksInAir = 0;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    public float damage = 5.0F;
    public int punch = 0;
    public boolean accelerate = false;
    public boolean explosive = false;
    public boolean explosiveDamage = true;
    public String hitSound;
    public int explosiveRadius = 0;
    public int duration = 5;
    public int amplify = 0;
    public EnumPotionType effect = EnumPotionType.None;
    public ImpactListener callback;
    public ItemStack callbackItem;

    public EntityProjectile(World par1World) {
        super(par1World);
        setSize(0.25F, 0.25F);
    }

    protected void entityInit() {
        this.dataWatcher.addObjectByDataType(21, 5);
        this.dataWatcher.addObject(22, String.valueOf(""));
        this.dataWatcher.addObject(23, Integer.valueOf(5));
        this.dataWatcher.addObject(24, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(25, Integer.valueOf(10));
        this.dataWatcher.addObject(26, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(27, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(28, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(29, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(30, Byte.valueOf((byte) 0));
        this.dataWatcher.addObject(31, Byte.valueOf((byte) 0));
    }

    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double par1) {
        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return par1 < d1 * d1;
    }

    public EntityProjectile(World par1World, EntityLivingBase par2EntityLiving, ItemStack item) {
        super(par1World);
        this.thrower = par2EntityLiving;
        if (this.thrower != null) {
            this.throwerName = this.thrower.getUniqueID().toString();
        }
        setThrownItem(item);
        this.dataWatcher.updateObject(27, Byte.valueOf((byte) (getItem() == Items.arrow ? 1 : 0)));
        setSize(this.dataWatcher.getWatchableObjectInt(23) / 10, this.dataWatcher.getWatchableObjectInt(23) / 10);
        setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.1F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.1F;
        setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
    }

    public void setThrownItem(ItemStack item) {
        this.dataWatcher.updateObject(21, item);
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        float yaw = (float) (Math.atan2(par1, par5) * 180.0D / 3.141592653589793D);
        float pitch = hasGravity() ? par7 : (float) (Math.atan2(par3, f3) * 180.0D / 3.141592653589793D);
        this.prevRotationYaw = (this.rotationYaw = yaw);
        this.prevRotationPitch = (this.rotationPitch = pitch);
        this.motionX = (MathHelper.sin(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F));
        this.motionZ = (MathHelper.cos(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F));
        this.motionY = MathHelper.sin((pitch + 1.0F) / 180.0F * 3.1415927F);
        this.motionX += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        this.motionZ += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        this.motionY += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        this.motionX *= getSpeed();
        this.motionZ *= getSpeed();
        this.motionY *= getSpeed();
        this.accelerationX = (par1 / f2 * 0.1D);
        this.accelerationY = (par3 / f2 * 0.1D);
        this.accelerationZ = (par5 / f2 * 0.1D);
        this.ticksInGround = 0;
    }

    public float getAngleForXYZ(double varX, double varY, double varZ, double horiDist, boolean arc) {
        float g = getGravityVelocity();
        float var1 = getSpeed() * getSpeed();
        double var2 = g * horiDist;
        double var3 = g * horiDist * horiDist + 2.0D * varY * var1;
        double var4 = var1 * var1 - g * var3;
        if (var4 < 0.0D) {
            return 30.0F;
        }
        float var6 = arc ? var1 + MathHelper.sqrt_double(var4) : var1 - MathHelper.sqrt_double(var4);
        float var7 = (float) (Math.atan2(var6, var2) * 180.0D / 3.141592653589793D);
        return var7;
    }

    public void shoot(float speed) {
        double varX = -MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F);
        double varZ = MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F);
        double varY = -MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F);
        setThrowableHeading(varX, varY, varZ, -this.rotationPitch, speed);
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        if ((this.worldObj.isRemote) && (this.inGround)) {
            return;
        }
        setPosition(par1, par3, par5);
        setRotation(par7, par8);
    }

    public void onUpdate() {
        super.onEntityUpdate();
        if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F)) {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = (this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));
            this.prevRotationPitch = (this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180.0D / 3.141592653589793D));
            if (isRotating()) {
                this.rotationPitch -= 20.0F;
            }
        }
        if ((this.effect == EnumPotionType.Fire) && (!this.inGround)) {
            setFire(1);
        }
        Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
        if (((isArrow()) || (sticksToWalls())) && (block != null)) {
            block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
            if ((axisalignedbb != null) && (axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            this.arrowShake -= 1;
        }
        if (this.inGround) {
            int j = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            if ((block == this.inTile) && (j == this.inData)) {
                this.ticksInGround += 1;
                if (this.ticksInGround == 1200) {
                    setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            this.ticksInAir += 1;
            if (this.ticksInAir == 1200) {
                setDead();
            }
            Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3, vec31, false, true, false);
            vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (movingobjectposition != null) {
                vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }
            if (!this.worldObj.isRemote) {
                Entity entity = null;
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
                double d0 = 0.0D;
                for (int k = 0; k < list.size(); k++) {
                    Entity entity1 = (Entity) list.get(k);
                    if ((entity1.canBeCollidedWith()) && ((!entity1.isEntityEqual(this.thrower)) || (this.ticksInAir >= 25))) {
                        float f = 0.3F;
                        AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
                        MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
                        if (movingobjectposition1 != null) {
                            double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
                            if ((d1 < d0) || (d0 == 0.0D)) {
                                entity = entity1;
                                d0 = d1;
                            }
                        }
                    }
                }
                if (entity != null) {
                    movingobjectposition = new MovingObjectPosition(entity);
                }
            }
            if (movingobjectposition != null) {
                if ((movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) && (this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal)) {
                    setInPortal();
                } else {
                    this.dataWatcher.updateObject(29, Byte.valueOf((byte) 0));
                    onImpact(movingobjectposition);
                }
            }
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = ((float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));
            for (this.rotationPitch = ((float) (Math.atan2(this.motionY, f1) * 180.0D / 3.141592653589793D)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }
            float f = isArrow() ? 0.0F : 225.0F;
            this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) + f * 0.2F);
            this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F);
            if (isRotating()) {
                int spin = isBlock() ? 10 : 20;
                this.rotationPitch -= this.ticksInAir % 15 * spin * getSpeed();
            }
            float f2 = getMotionFactor();
            float f3 = getGravityVelocity();
            if (isInWater()) {
                if (this.worldObj.isRemote) {
                    for (int k = 0; k < 4; k++) {
                        float f4 = 0.25F;
                        this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
                    }
                }
                f2 = 0.8F;
            }
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
            if (hasGravity()) {
                this.motionY -= f3;
            }
            if (this.accelerate) {
                this.motionX += this.accelerationX;
                this.motionY += this.accelerationY;
                this.motionZ += this.accelerationZ;
            }
            if ((this.worldObj.isRemote) && (!this.dataWatcher.getWatchableObjectString(22).equals(""))) {
                this.worldObj.spawnParticle(this.dataWatcher.getWatchableObjectString(22), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            setPosition(this.posX, this.posY, this.posZ);
            func_145775_I();
        }
    }

    public boolean isBlock() {
        ItemStack item = getItemDisplay();
        if (item == null) {
            return false;
        }
        return item.getItem() instanceof ItemBlock;
    }

    private Item getItem() {
        ItemStack item = getItemDisplay();
        if (item == null) {
            return null;
        }
        return item.getItem();
    }

    protected float getMotionFactor() {
        return this.accelerate ? 0.95F : 1.0F;
    }

    protected void onImpact(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entityHit != null) {
            if ((this.callback != null) && (this.callbackItem != null) && ((movingobjectposition.entityHit instanceof EntityLivingBase)) && (this.callback.onImpact(this, (EntityLivingBase) movingobjectposition.entityHit, this.callbackItem))) {
                return;
            }
            float damage = this.damage;
            if (damage == 0.0F) {
                damage = 0.001F;
            }
            if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage)) {
                if (((movingobjectposition.entityHit instanceof EntityLivingBase)) && ((isArrow()) || (sticksToWalls()))) {
                    EntityLivingBase entityliving = (EntityLivingBase) movingobjectposition.entityHit;
                    if (!this.worldObj.isRemote) {
                        entityliving.setArrowCountInEntity(entityliving.getArrowCountInEntity() + 1);
                    }
                    if ((this.destroyedOnEntityHit) && (!(movingobjectposition.entityHit instanceof EntityEnderman))) {
                        setDead();
                    }
                }
                if (isBlock()) {
                    this.worldObj.playAuxSFX(2001, (int) movingobjectposition.entityHit.posX, (int) movingobjectposition.entityHit.posY, (int) movingobjectposition.entityHit.posZ, Item.getIdFromItem(getItem()));
                } else if ((!isArrow()) && (!sticksToWalls())) {
                    for (int i = 0; i < 8; i++) {
                        this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(getItem()), this.posX, this.posY, this.posZ, this.rand.nextGaussian() * 0.15D, this.rand.nextGaussian() * 0.2D, this.rand.nextGaussian() * 0.15D);
                    }
                }
                if (this.punch > 0) {
                    float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                    if (f3 > 0.0F) {
                        movingobjectposition.entityHit.addVelocity(this.motionX * this.punch * 0.6000000238418579D / f3, 0.1D, this.motionZ * this.punch * 0.6000000238418579D / f3);
                    }
                }
                if ((this.effect != EnumPotionType.None) && ((movingobjectposition.entityHit instanceof EntityLivingBase))) {
                    if (this.effect != EnumPotionType.Fire) {
                        int p = getPotionEffect(this.effect);
                        ((EntityLivingBase) movingobjectposition.entityHit).addPotionEffect(new PotionEffect(p, this.duration * 20, this.amplify));
                    } else {
                        movingobjectposition.entityHit.setFire(this.duration);
                    }
                }
            } else if ((hasGravity()) && ((isArrow()) || (sticksToWalls()))) {
                this.motionX *= -0.1;
                this.motionY *= -0.12;
                this.motionZ *= -0.1;
                this.rotationYaw += 180.0F;
                this.prevRotationYaw += 180.0F;
                this.ticksInAir = 0;
            }
        } else if ((isArrow()) || (sticksToWalls())) {
            this.xTile = movingobjectposition.blockX;
            this.yTile = movingobjectposition.blockY;
            this.zTile = movingobjectposition.blockZ;
            this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
            this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.posX));
            this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.posY));
            this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
            float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / f2 * 0.05000000074505806D;
            this.posY -= this.motionY / f2 * 0.05000000074505806D;
            this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
            this.inGround = true;
            if (hitSound == null){
                if (isArrow()) {
                    playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                } else {
                    playSound("random.break", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                }
            } else {
                playSound(hitSound, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            }
            this.arrowShake = 7;
            if (!hasGravity()) {
                this.dataWatcher.updateObject(26, Byte.valueOf((byte) 1));
            }
            if (this.inTile != null) {
                this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
            }
        } else if (isBlock()) {
            this.worldObj.playAuxSFX(2001, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), Item.getIdFromItem(getItem()));
        } else {
            for (int i = 0; i < 8; i++) {
                this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(getItem()), this.posX, this.posY, this.posZ, this.rand.nextGaussian() * 0.15D, this.rand.nextGaussian() * 0.2D, this.rand.nextGaussian() * 0.15D);
            }
        }
        if (this.explosive) {
            if ((this.explosiveRadius != 0) || (this.effect == EnumPotionType.None)) {
                boolean terraindamage = (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) && (this.explosiveDamage);
                Explosion explosion = new Explosion(this.worldObj, this, this.posX, this.posY, this.posZ, this.explosiveRadius);
                explosion.isFlaming = (this.effect == EnumPotionType.Fire);
                explosion.isSmoking = terraindamage;
                if (terraindamage) {
                    explosion.doExplosionA();
                }
                explosion.doExplosionB(this.worldObj.isRemote);
                if (!this.worldObj.isRemote) {
                    Iterator iterator = this.worldObj.playerEntities.iterator();
                    while (iterator.hasNext()) {
                        EntityPlayer entityplayer = (EntityPlayer) iterator.next();
                        if (entityplayer.getDistanceSq(this.posX, this.posY, this.posZ) < 4096.0D) {
                            ((EntityPlayerMP) entityplayer).playerNetServerHandler.sendPacket(new S27PacketExplosion(this.posX, this.posY, this.posZ, this.explosiveRadius, explosion.affectedBlockPositions, (Vec3) explosion.func_77277_b().get(entityplayer)));
                        }
                    }
                }
                if ((this.explosiveRadius != 0) && ((isArrow()) || (sticksToWalls()))) {
                    setDead();
                }
            } else if (this.effect == EnumPotionType.Fire) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;
                switch (movingobjectposition.sideHit) {
                    case 0:
                        j--;
                        break;
                    case 1:
                        j++;
                        break;
                    case 2:
                        k--;
                        break;
                    case 3:
                        k++;
                        break;
                    case 4:
                        i--;
                        break;
                    case 5:
                        i++;
                }
                if (this.worldObj.isAirBlock(i, j, k)) {
                    this.worldObj.setBlock(i, j, k, Blocks.fire);
                }
            } else {
                AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
                List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
                if ((list1 != null) && (!list1.isEmpty())) {
                    Iterator iterator = list1.iterator();
                    while (iterator.hasNext()) {
                        EntityLivingBase entitylivingbase = (EntityLivingBase) iterator.next();
                        double d0 = getDistanceSqToEntity(entitylivingbase);
                        if (d0 < 16.0D) {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                            if (entitylivingbase == movingobjectposition.entityHit) {
                                d1 = 1.0D;
                            }
                            int i = getPotionEffect(this.effect);
                            if (Potion.potionTypes[i].isInstant()) {
                                Potion.potionTypes[i].affectEntity(getThrower(), entitylivingbase, this.amplify, d1);
                            } else {
                                int j = (int) (d1 * this.duration + 0.5D);
                                if (j > 20) {
                                    entitylivingbase.addPotionEffect(new PotionEffect(i, j, this.amplify));
                                }
                            }
                        }
                    }
                }
                this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), getPotionColor(this.effect));
            }
        }
        if ((!this.worldObj.isRemote) && (!isArrow()) && (!sticksToWalls())) {
            setDead();
        }
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short) this.xTile);
        par1NBTTagCompound.setShort("yTile", (short) this.yTile);
        par1NBTTagCompound.setShort("zTile", (short) this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
        par1NBTTagCompound.setByte("inData", (byte) this.inData);
        par1NBTTagCompound.setByte("shake", (byte) this.throwableShake);
        par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        par1NBTTagCompound.setByte("isArrow", (byte) (isArrow() ? 1 : 0));
        par1NBTTagCompound.setTag("direction", newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
        par1NBTTagCompound.setBoolean("canBePickedUp", this.canBePickedUp);
        if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && ((this.thrower instanceof EntityPlayer))) {
            this.throwerName = this.thrower.getUniqueID().toString();
        }
        par1NBTTagCompound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
        if (getItemDisplay() != null) {
            par1NBTTagCompound.setTag("Item", getItemDisplay().writeToNBT(new NBTTagCompound()));
        }
        par1NBTTagCompound.setFloat("damagev2", this.damage);
        par1NBTTagCompound.setInteger("punch", this.punch);
        par1NBTTagCompound.setInteger("size", this.dataWatcher.getWatchableObjectInt(23));
        par1NBTTagCompound.setInteger("velocity", this.dataWatcher.getWatchableObjectInt(25));
        par1NBTTagCompound.setInteger("explosiveRadius", this.explosiveRadius);
        par1NBTTagCompound.setInteger("effectDuration", this.duration);
        par1NBTTagCompound.setBoolean("gravity", hasGravity());
        par1NBTTagCompound.setBoolean("accelerate", this.accelerate);
        par1NBTTagCompound.setByte("glows", this.dataWatcher.getWatchableObjectByte(24));
        par1NBTTagCompound.setBoolean("explosive", this.explosive);
        par1NBTTagCompound.setInteger("PotionEffect", this.effect.ordinal());
        par1NBTTagCompound.setString("trail", this.dataWatcher.getWatchableObjectString(22));
        par1NBTTagCompound.setByte("Render3D", this.dataWatcher.getWatchableObjectByte(28));
        par1NBTTagCompound.setByte("Spins", this.dataWatcher.getWatchableObjectByte(29));
        par1NBTTagCompound.setByte("Sticks", this.dataWatcher.getWatchableObjectByte(30));
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 0xFF);
        this.inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
        this.throwableShake = (par1NBTTagCompound.getByte("shake") & 0xFF);
        this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
        this.dataWatcher.updateObject(27, Byte.valueOf(par1NBTTagCompound.getByte("isArrow")));
        this.throwerName = par1NBTTagCompound.getString("ownerName");
        this.canBePickedUp = par1NBTTagCompound.getBoolean("canBePickedUp");

        this.damage = par1NBTTagCompound.getFloat("damagev2");
        this.punch = par1NBTTagCompound.getInteger("punch");
        this.explosiveRadius = par1NBTTagCompound.getInteger("explosiveRadius");
        this.duration = par1NBTTagCompound.getInteger("effectDuration");
        this.accelerate = par1NBTTagCompound.getBoolean("accelerate");
        this.explosive = par1NBTTagCompound.getBoolean("explosive");
        this.effect = EnumPotionType.values()[(par1NBTTagCompound.getInteger("PotionEffect") % EnumPotionType.values().length)];
        this.dataWatcher.updateObject(22, par1NBTTagCompound.getString("trail"));
        this.dataWatcher.updateObject(23, Integer.valueOf(par1NBTTagCompound.getInteger("size")));
        this.dataWatcher.updateObject(24, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("glows") ? 1 : 0)));
        this.dataWatcher.updateObject(25, Integer.valueOf(par1NBTTagCompound.getInteger("velocity")));
        this.dataWatcher.updateObject(26, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("gravity") ? 1 : 0)));
        this.dataWatcher.updateObject(28, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("Render3D") ? 1 : 0)));
        this.dataWatcher.updateObject(29, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("Spins") ? 1 : 0)));
        this.dataWatcher.updateObject(30, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("Sticks") ? 1 : 0)));
        if ((this.throwerName != null) && (this.throwerName.length() == 0)) {
            this.throwerName = null;
        }
        if (par1NBTTagCompound.hasKey("direction")) {
            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("direction", 6);
            this.motionX = nbttaglist.func_150309_d(0);
            this.motionY = nbttaglist.func_150309_d(1);
            this.motionZ = nbttaglist.func_150309_d(2);
        }
        NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Item");
        ItemStack item = ItemStack.loadItemStackFromNBT(var2);
        if (item == null) {
            setDead();
        } else {
            this.dataWatcher.updateObject(21, item);
        }
    }

    public EntityLivingBase getThrower() {
        if ((this.throwerName == null) || (this.throwerName.isEmpty())) {
            return null;
        }
        try {
            UUID uuid = UUID.fromString(this.throwerName);
            if ((this.thrower == null) && (uuid != null)) {
                this.thrower = this.worldObj.func_152378_a(uuid);
            }
        } catch (IllegalArgumentException ex) {
        }
        return this.thrower;
    }

    private int getPotionEffect(EnumPotionType p) {
        switch (p) {
            case Poison:
                return Potion.poison.id;
            case Hunger:
                return Potion.hunger.id;
            case Weakness:
                return Potion.weakness.id;
            case Slowness:
                return Potion.moveSlowdown.id;
            case Nausea:
                return Potion.confusion.id;
            case Blindness:
                return Potion.blindness.id;
            case Wither:
                return Potion.wither.id;
        }
        return 0;
    }

    private int getPotionColor(EnumPotionType p) {
        switch (p) {
            case Poison:
                return 32660;
            case Hunger:
                return 32660;
            case Weakness:
                return 32696;
            case Slowness:
                return 32698;
            case Nausea:
                return 32732;
            case Blindness:
                return Potion.blindness.id;
            case Wither:
                return 32732;
        }
        return 0;
    }

    public void setParticleEffect(EnumParticleType type) {
        this.dataWatcher.updateObject(22, type.particleName);
    }

    public void setHasGravity(boolean bo) {
        this.dataWatcher.updateObject(26, Byte.valueOf((byte) (bo ? 1 : 0)));
    }

    public void setIs3D(boolean bo) {
        this.dataWatcher.updateObject(28, Byte.valueOf((byte) (bo ? 1 : 0)));
    }

    public void setStickInWall(boolean bo) {
        this.dataWatcher.updateObject(30, Byte.valueOf((byte) (bo ? 1 : 0)));
    }

    public ItemStack getItemDisplay() {
        return this.dataWatcher.getWatchableObjectItemStack(21);
    }

    public float getBrightness(float par1) {
        return this.dataWatcher.getWatchableObjectByte(24) == 1 ? 1.0F : super.getBrightness(par1);
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return this.dataWatcher.getWatchableObjectByte(24) == 1 ? 15728880 : super.getBrightnessForRender(par1);
    }

    public boolean hasGravity() {
        return this.dataWatcher.getWatchableObjectByte(26) == 1;
    }

    public void setSpeed(int speed) {
        this.dataWatcher.updateObject(25, Integer.valueOf(speed));
    }

    public float getSpeed() {
        return this.dataWatcher.getWatchableObjectInt(25) / 10.0F;
    }

    public boolean isArrow() {
        return this.dataWatcher.getWatchableObjectByte(27) == 1;
    }

    public void setArrow(boolean bo) {
        this.dataWatcher.updateObject(27, Byte.valueOf((byte) (bo ? 1 : 0)));
    }

    public void setRotating(boolean bo) {
        this.dataWatcher.updateObject(29, Byte.valueOf((byte) (bo ? 1 : 0)));
    }

    public boolean isRotating() {
        return this.dataWatcher.getWatchableObjectByte(29) == 1;
    }

    public boolean glows() {
        return this.dataWatcher.getWatchableObjectByte(24) == 1;
    }

    public boolean is3D() {
        return (this.dataWatcher.getWatchableObjectByte(28) == 1) || (isBlock());
    }

    public boolean sticksToWalls() {
        return (is3D()) && (this.dataWatcher.getWatchableObjectByte(30) == 1);
    }

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if ((this.worldObj.isRemote) || (!this.canBePickedUp) || (!this.inGround) || (this.arrowShake > 0)) {
            return;
        }
        if ((!onlyCanBePickedUpByThrower || par1EntityPlayer.equals(thrower)) && par1EntityPlayer.inventory.addItemStackToInventory(getItemDisplay())) {
            this.inGround = false;
            playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            par1EntityPlayer.onItemPickup(this, 1);
            setDead();
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public IChatComponent func_145748_c_() {
        if (getItemDisplay() != null) {
            return new ChatComponentTranslation(getItemDisplay().getDisplayName(), new Object[0]);
        }
        return super.func_145748_c_();
    }

    public enum EnumPotionType {
        None, Fire, Poison, Hunger, Weakness, Slowness, Nausea, Blindness, Wither;
    }

    public enum EnumParticleType {
        None(""), Smoke("smoke"), Portal("portal"), Redstone("reddust"), Lightning("magicCrit"), LargeSmoke("largesmoke"), Magic("witchMagic"), Enchant("enchantmenttable"), Crit("crit");

        public String particleName;

        EnumParticleType(String name) {
            this.particleName = name;
        }
    }

    public interface ImpactListener {
        boolean onImpact(EntityProjectile paramEntityProjectile, EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack);
    }
}