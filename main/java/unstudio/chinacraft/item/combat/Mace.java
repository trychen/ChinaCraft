package unstudio.chinacraft.item.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import unstudio.chinacraft.common.ChinaCraft;

public class Mace extends ItemSword{
    public Mace() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("mace");
    }
    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase target, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.damageItem(1, p_77644_3_);
        target.addPotionEffect(new PotionEffect(4,10));
        target.addPotionEffect(new PotionEffect(2,10));
        return true;
    }
}
