package unstudio.chinacraft.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockRule {

    private Block block;
    private int data;

    public BlockRule(Block block) {
        this.setRule(block, -1);
    }

    public BlockRule(Block block, int data) {
        this.setRule(block, data);
    }

    public Block getBlock() {
        return this.block;
    }

    public int getData() {
        return this.data;
    }

    public void setRule(Block block, int data) {
        this.block = block;
        this.data = data;
    }

    public boolean check(World world, int X, int Y, int Z) {
        return this.check(world.getBlock(X, Y, Z), world.getBlockMetadata(X, Y, Z));
    }

    public boolean check(Block block, int data) {
        if (block == null)
            return false;
        if (!block.equals(this.block))
            return false;
        if (this.data != -1 && data != this.data)
            return false;

        return true;
    }

    public BlockRule copy() {
        return new BlockRule(this.block, this.data);
    }
}
