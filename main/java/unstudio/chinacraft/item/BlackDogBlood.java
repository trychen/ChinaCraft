package unstudio.chinacraft.item;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import unstudio.chinacraft.ChinaCraft;


public class BlackDogBlood extends ItemFood {
    public BlackDogBlood() {
        super(0, false);
        this.setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabCore);
        setUnlocalizedName("BlackDogBlood");
    }

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
        p_77654_3_.addPotionEffect(new PotionEffect(17,200));
        return new ItemStack(Items.bowl);
    }
}
