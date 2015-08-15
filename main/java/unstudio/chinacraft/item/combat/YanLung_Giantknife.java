package unstudio.chinacraft.item.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import unstudio.chinacraft.ChinaCraft;

public class YanLung_Giantknife extends ItemSword{
	public YanLung_Giantknife() {
		super(ChinaCraft.yanlong);
		setUnlocalizedName("yanlung_giantknife");
		setMaxStackSize(1);
		setMaxDamage(1500);
		setCreativeTab(ChinaCraft.tabTool);
	}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		if (target.worldObj.rand.nextBoolean()) {
			target.setFire(target.worldObj.rand.nextInt(5) + 1);
		}
		return true;
	}
}