package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;

public class ItemBlackDogBlood extends ItemFood {
    public ItemBlackDogBlood() {
        super(0, false);
        this.setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabCore);
        setUnlocalizedName("black_dog_blood");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onItemUseFinish(stack, worldIn, playerIn);
        playerIn.addPotionEffect(new PotionEffect(17, 200));
        return new ItemStack(Items.bowl);
    }
}
