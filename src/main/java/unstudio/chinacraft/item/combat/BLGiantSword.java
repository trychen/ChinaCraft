package unstudio.chinacraft.item.combat;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.client.resources.I18n;

import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;

public class BLGiantSword extends ItemSword implements ISpecialEquippedRender{
    public BLGiantSword(ToolMaterial toolMaterial) {
        super(toolMaterial);
        setUnlocalizedName("yanlung_giantknife");
        setMaxStackSize(1);
        setMaxDamage(1500);
        setCreativeTab(ChinaCraft.tabTool);
    }

    @Override
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        target.setFire(1);
        if (target.worldObj.rand.nextBoolean()) {
            target.setFire(target.worldObj.rand.nextInt(5) + 1);
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(I18n.format("item.yanlung_giantknife.lore"));
    }

    @Override
    public void doRender() {
    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.huge;
    }
}
