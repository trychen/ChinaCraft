package unstudio.chinacraft.block.decoration;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * Created by trychen on 17/7/17.
 */
public class BlockCCDoor extends BlockDoor {
    private Item item;
    public BlockCCDoor(Material material) {
        super(material);
    }

    public Item getItem() {
        return item;
    }
}
