package unstudio.chinacraft.item.combat;


import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.item.BroadItemIcon;

public class BronzeBroadSword extends ItemSword{
//	private BroadItemIcon broadIcon;
//	private IIcon basicIcon;
	public BronzeBroadSword(String name) {
		super(ChinaCraft._BRONZE);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(ChinaCraft.tabTool);
	}
//
//	@Override
//	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
//		return par2ItemStack.getItem() == ChinaCraft.bronzeIngot ? true:super.getIsRepairable(par1ItemStack,par2ItemStack);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister iIconRegister) {
//		this.basicIcon = iIconRegister.registerIcon("chinacraft:"+getUnlocalizedName().substring(5));
//		this.broadIcon = new BroadItemIcon(this.basicIcon, 0.1875F, 0.3125F);
//	}
//
//	public IIcon getIcon(ItemStack stack, int pass)
//	{
//		if (pass == -1) {
//			return this.broadIcon;
//		}
//		return basicIcon;
//	}
}
