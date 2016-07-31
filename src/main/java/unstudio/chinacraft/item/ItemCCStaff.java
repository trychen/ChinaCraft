package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

/**
 * Created by trychen on 16/7/31.
 */
public class ItemCCStaff extends Item implements ISpecialEquippedRender{
    public ItemCCStaff() {
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
        setFull3D();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public void doRender() {
    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.staff;
    }
}
