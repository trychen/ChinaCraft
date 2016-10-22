package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.projectile.EntityProjectile;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

/**
 * Created by trychen on 16/10/22.
 */
public class CCItemThrowable extends Item implements ISpecialEquippedRender {
    private float damage = 1.0f;
    private boolean rotating = false;
    private boolean dropItem = false;

    public CCItemThrowable(float damage, boolean rotating, boolean dropItem) {
        this.damage = damage;
        this.rotating = rotating;
        this.dropItem = dropItem;
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
        projectile.canBePickedUp = ((!player.capabilities.isCreativeMode) && (this.dropItem));
        projectile.setRotating(this.rotating);
        projectile.damage = this.damage;
        projectile.setIs3D(true);
        projectile.setStickInWall(true);
        projectile.setHasGravity(true);
        projectile.setSpeed(36);
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
}
