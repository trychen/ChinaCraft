package unstudio.sinocraft.item.combat;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

import unstudio.sinocraft.common.SinoCraft;

public class Mace extends ItemSword {
    public Mace() {
        super(ToolMaterial.IRON);
        setCreativeTab(SinoCraft.tabTool);
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
        p_77624_3_.add(StatCollector.translateToLocal("item.mace.lore"));
    }
}
