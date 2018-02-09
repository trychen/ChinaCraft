package unstudio.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.NotImplementedException;
import unstudio.chinacraft.common.ChinaCraft;

public class CCItemLatticeDoor extends Item {

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (meta != 1) {
            return false;
        } else {
            ++y;
            throw new NotImplementedException("Feature Not Implemented");
//            Block block = ChinaCraft.latticeDoor;
//            Block block = Blocks.air;
//            if (player.canPlayerEdit(x, y, z, meta, itemStack) && player.canPlayerEdit(x, y + 1, z, meta, itemStack)) {
//                if (!block.canPlaceBlockAt(w, x, y, z)) {
//                    return false;
//                } else {
//                    int i1 = MathHelper.floor_double((double) ((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
//                    placeDoorBlock(w, x, y, z, i1, block);
//                    --itemStack.stackSize;
//                    return true;
//                }
//            } else {
//                return false;
//            }
        }
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int meta, Block block) {
        byte b0 = 0;
        byte b1 = 0;

        if (meta == 0) {
            b1 = 1;
        }

        if (meta == 1) {
            b0 = -1;
        }

        if (meta == 2) {
            b1 = -1;
        }

        if (meta == 3) {
            b0 = 1;
        }

        int i1 = (world.getBlock(x - b0, y, z - b1).isNormalCube() ? 1 : 0) + (world.getBlock(x - b0, y + 2, z - b1).isNormalCube() ? 1 : 0);
        int j1 = (world.getBlock(x + b0, y, z + b1).isNormalCube() ? 1 : 0) + (world.getBlock(x + b0, y + 2, z + b1).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlock(x - b0, y, z - b1) == block || world.getBlock(x - b0, y + 2, z - b1) == block;
        boolean flag1 = world.getBlock(x + b0, y, z + b1) == block || world.getBlock(x + b0, y + 2, z + b1) == block;
        boolean flag2 = false;

        if (flag && !flag1) {
            flag2 = true;
        } else if (j1 > i1) {
            flag2 = true;
        }

        world.setBlock(x, y, z, block, meta, 2);
        world.setBlock(x, y + 1, z, block, meta, 2);
        world.setBlock(x, y + 2, z, block, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 2, z, block);
    }
}
