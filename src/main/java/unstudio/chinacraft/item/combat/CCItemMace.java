package unstudio.chinacraft.item.combat;

import java.util.List;

import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class CCItemMace extends ItemSword implements ISpecialEquippedRender{
    public CCItemMace() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("mace");
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer){
            if (((EntityPlayer) entity).onGround && target.worldObj.rand.nextBoolean()){
                ((EntityPlayer)entity).onCriticalHit(target);
                itemStack.damageItem(3, entity);
            } else {
                itemStack.damageItem(1, entity);
            }
        } else
            itemStack.damageItem(5, entity);
        return true;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(StatCollector.translateToLocal("item.mace.lore"));
    }

    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.huge;
    }

    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 3.0, 0));
        return multimap;
    }
}
