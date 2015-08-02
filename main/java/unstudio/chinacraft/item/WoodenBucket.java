package unstudio.chinacraft.item;

import cpw.mods.fml.common.eventhandler.Event;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class WoodenBucket extends Item{

	public WoodenBucket() {
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabCore);
		setUnlocalizedName("wooden_bucket");
	}
	
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
    	if(player.isSneaking()) {
    		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
            if (movingobjectposition == null)
            {
                return item;
            }else {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;
                if (movingobjectposition.sideHit == 0)
                {
                    --j;
                }

                if (movingobjectposition.sideHit == 1)
                {
                    ++j;
                }

                if (movingobjectposition.sideHit == 2)
                {
                    --k;
                }

                if (movingobjectposition.sideHit == 3)
                {
                    ++k;
                }

                if (movingobjectposition.sideHit == 4)
                {
                    --i;
                }

                if (movingobjectposition.sideHit == 5)
                {
                    ++i;
                }

                if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item))
                {
                    return item;
                }

                if (world.setBlock(i, j, k, ChinaCraft.blockWoodenBucket, 0, 2) && !player.capabilities.isCreativeMode)
                {
                    if(--item.stackSize <= 0) {
                    	return item;
                    }
                }
            }
    	}
    	super.onItemRightClick(item, world, player);
		return item;
        }
}
