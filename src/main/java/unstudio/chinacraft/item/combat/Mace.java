package unstudio.chinacraft.item.combat;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.client.resources.I18n;

import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class Mace extends ItemSword implements ISpecialEquippedRender{
    public Mace() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("mace");
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase target, EntityLivingBase p_77644_3_) {
        p_77644_1_.damageItem(1, p_77644_3_);
        target.addPotionEffect(new PotionEffect(4, 8));
        target.addPotionEffect(new PotionEffect(2, 8));
        return true;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(I18n.format("item.mace.lore"));
    }

    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.huge;
    }
}
