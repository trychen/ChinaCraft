package unstudio.chinacraft.util;

import java.util.HashMap;
import java.util.Map;

public class PotteryManager {

    private static PotteryManager instance = new PotteryManager();
    private Map<String, BlockPottery> blockPotteryMap;
    private Map<String, ItemPottery> itemPotteryMap;
    public PotteryManager() {
        blockPotteryMap = new HashMap<String, BlockPottery>();
        itemPotteryMap = new HashMap<String, ItemPottery>();
    }

    public static PotteryManager Instance() {
        return instance;
    }

    public BlockPottery addBlockPottery(String name, BlockPottery blockPottery) {
        if (blockPotteryMap.get(name) != null)
            return null;
        blockPotteryMap.put(name, blockPottery);
        return blockPottery;
    }

    public ItemPottery addItemPottery(String name, ItemPottery itemPottery) {
        if (itemPotteryMap.get(name) != null)
            return null;
        itemPotteryMap.put(name, itemPottery);
        return itemPottery;
    }

    public BlockPottery getBlockPottery(String name) {
        return blockPotteryMap.get(name);
    }

    public void initCommon() {

    }

    public void initCilent() {

    }
}
