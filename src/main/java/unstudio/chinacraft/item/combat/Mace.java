package unstudio.chinacraft.item.combat;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.List;

public class Mace extends ItemSword {
    public Mace() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("mace");
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase target, EntityLivingBase p_77644_3_) {
        p_77644_1_.damageItem(1, p_77644_3_);
        target.addPotionEffect(new PotionEffect(Potion.getPotionById(4), 8));
        target.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 8));
        return true;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(I18n.format("item.mace.lore"));
    }

}
