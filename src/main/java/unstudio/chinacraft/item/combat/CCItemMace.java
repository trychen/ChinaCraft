package unstudio.chinacraft.item.combat;

import java.util.List;

import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.fx.FxHelper;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class CCItemMace extends ItemSword implements ISpecialEquippedRender{
    public CCItemMace() {
        super(ChinaCraft.MACE);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("mace");
    }

    @Override
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        ItemStack mat = new ItemStack(Blocks.obsidian, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE);
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
        return super.getIsRepairable(p_82789_1_, p_82789_2_);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(StatCollector.translateToLocal("item.mace.lore"));
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        return super.hitEntity(p_77644_1_, p_77644_2_, p_77644_3_);
    }

    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.huge;
    }
}
