package unstudio.chinacraft.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;

import unstudio.chinacraft.api.ItemMethod;

public class ItemSMFFire extends Item {
    public ItemSMFFire() {
        setUnlocalizedName("spiritual_magic_figures_fire");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target.worldObj.isRemote) {
            target.setFire(target.worldObj.rand.nextInt(8) + 15);
            stack.stackSize--;
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            player.setFire(15);
            return ItemMethod.cutItemStack(stack, player);
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(I18n.format("item.spiritual_magic_figures_fire.lore"));
    }
}
