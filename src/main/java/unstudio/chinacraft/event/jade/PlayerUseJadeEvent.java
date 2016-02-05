package unstudio.chinacraft.event.jade;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Created by trych on 2016/1/9.
 */

public class PlayerUseJadeEvent extends PlayerEvent
{
    public final ItemStack itemStack;
    public PlayerUseJadeEvent(EntityPlayer player,ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }

    @Cancelable
    public static class ItemRightClick extends PlayerUseJadeEvent{
        public ItemRightClick(EntityPlayer player, ItemStack itemStack) {
            super(player, itemStack);
        }
    }

    @Cancelable
    public static class MainInventory extends PlayerUseJadeEvent{

        public MainInventory(EntityPlayer player, ItemStack itemStack) {
            super(player, itemStack);
        }
    }
}
