package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.projectile.EntityProjectile;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class CCItemThrowable extends Item implements ISpecialEquippedRender {
    private float damage = 1.0f;
    private boolean rotating = false;
    private boolean dropItem = false;
    private boolean hasGravity = true;
    private String hitSound;
    private boolean stickInWall = true;
    public boolean onlyCanBePickedUpByThrower = true;
    private boolean is3D = true;
    private EntityProjectile.EnumPotionType effect = EntityProjectile.EnumPotionType.None;
    private EntityProjectile.EnumParticleType particleType = EntityProjectile.EnumParticleType.None;
    private ShootListsner listener;
    private int speed = 10;

    /**
     * 投掷型物品
     * @param damage 伤害
     * @param rotating 是否旋转
     * @param dropItem 是否可以捡起
     */
    public CCItemThrowable(float damage, boolean rotating, boolean dropItem) {
        this();
        this.damage = damage;
        this.rotating = rotating;
        this.dropItem = dropItem;
    }

    public CCItemThrowable() {
        this.setCreativeTab(ChinaCraft.tabTool);
        this.setMaxStackSize(16);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World worldObj, EntityPlayer player, int p_77615_4_) {
        if (worldObj.isRemote) {
            player.swingItem();
            return;
        }

        EntityProjectile projectile = new EntityProjectile(worldObj, player, new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage()));
        projectile.canBePickedUp = player.capabilities.isCreativeMode || this.dropItem;
        projectile.setRotating(this.rotating);
        projectile.damage = this.damage;
        projectile.effect = effect;
        projectile.hitSound = hitSound;
        projectile.onlyCanBePickedUpByThrower = onlyCanBePickedUpByThrower;
        projectile.setIs3D(is3D);
        projectile.setParticleEffect(particleType);
        projectile.setStickInWall(stickInWall);
        projectile.setHasGravity(hasGravity);
        projectile.setSpeed(speed);
        if (listener!=null) listener.onShoot(projectile,itemStack);
        if (!player.capabilities.isCreativeMode) {
            player.inventory.consumeInventoryItem(this);
        }
        projectile.shoot(1.0F);
        worldObj.spawnEntityInWorld(projectile);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }

    @Override
    public void doRender() {
    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.shuriken;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }

    public CCItemThrowable setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public CCItemThrowable setRotating(boolean rotating) {
        this.rotating = rotating;
        return this;
    }

    public CCItemThrowable setDropItem(boolean dropItem) {
        this.dropItem = dropItem;
        return this;
    }

    public CCItemThrowable setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
        return this;
    }

    public CCItemThrowable setStickInWall(boolean stickInWall) {
        this.stickInWall = stickInWall;
        return this;
    }

    public CCItemThrowable setIs3D(boolean is3D) {
        this.is3D = is3D;
        return this;
    }

    public CCItemThrowable setHitSound(String hitSound) {
        this.hitSound = hitSound;
        return this;
    }

    public CCItemThrowable setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public CCItemThrowable setParticleType(EntityProjectile.EnumParticleType particleType) {
        this.particleType = particleType;
        return this;
    }

    public CCItemThrowable setEffect(EntityProjectile.EnumPotionType effect) {
        this.effect = effect;
        return this;
    }

    public CCItemThrowable setListener(ShootListsner listener) {
        this.listener = listener;
        return this;
    }

    public float getDamage() {
        return damage;
    }

    public boolean isRotating() {
        return rotating;
    }

    public boolean isDropItem() {
        return dropItem;
    }

    public boolean isHasGravity() {
        return hasGravity;
    }

    public boolean isStickInWall() {
        return stickInWall;
    }

    public boolean is3D() {
        return is3D;
    }

    public int getSpeed() {
        return speed;
    }

    public String getHitSound() {
        return hitSound;
    }

    public EntityProjectile.EnumPotionType getEffect() {
        return effect;
    }

    interface ShootListsner{
        void onShoot(EntityProjectile projectile,ItemStack itemStack);
    }
}
