package unstudio.chinacraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class CCShield extends Item implements ISpecialEquippedRender {
    private float defenseProjectile;
    private float defenseAttack;

    /**
     * @param defenseProjectile 抵挡抛射物
     * @param defenseAttack 抵挡普通攻击
     */
    public CCShield(float defenseProjectile, float defenseAttack, boolean justInUse) {
        this.defenseProjectile = defenseProjectile;
        this.defenseAttack = defenseAttack;
        this.setCreativeTab(ChinaCraft.tabTool);
        this.setMaxStackSize(1);
    }

    @Override
    public void doRender() {
    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.shield;
    }

    /**
     * 获取最终受到的伤害
     * @param stack 物品
     * @param attacker 攻击者
     * @param amount 伤害
     */
    public float defense(ItemStack stack, EntityPlayer player, DamageSource attacker, float amount) {
        if (attacker.isProjectile() && defenseProjectile != 0){
            return amount * defenseProjectile;
        }
        return amount;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.web;
    }

    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            p_150894_1_.damageItem(2, p_150894_7_);
        }

        return true;
    }

}
