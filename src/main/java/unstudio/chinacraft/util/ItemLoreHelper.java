package unstudio.chinacraft.util;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by trychen on 16/11/5.
 */
public class ItemLoreHelper {
    /**
     * 添加可以Shift显示的Lore
     * @param lores addInformation 的arg3 的列表
     * @param def 未按下shift时显示的内容
     * @param shifts 按下shift后显示的内容
     */
    public static void shiftLore(List lores, String[] def, String[] shifts){
        for (String s : def)
            lores.add(s);
        
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            for (String s : shifts)
                lores.add(EnumChatFormatting.WHITE + s);
        else 
            lores.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("gui.inventory.shiftfordetail"));
    }

    /**
     * 添加可以Shift显示的Lore
     * @param lores addInformation 的arg3 的列表
     * @param itemName 未按下shift时显示的内容
     */
    public static void shiftLoreWithStat(List lores, String itemName){
        if (StatCollector.canTranslate(itemName + ".lore"))
            lores.add(StatCollector.translateToLocal(itemName + ".lore"));
        else {
            int i = 1;
            while (StatCollector.canTranslate(itemName + ".lore." + i)){
                lores.add(StatCollector.translateToLocal(itemName + ".lore." + i));
                i++;
            }
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            if (StatCollector.canTranslate(itemName + ".hidelore"))
                lores.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal(itemName + ".hidelore"));
            else {
                int i = 1;
                while (StatCollector.canTranslate(itemName + ".hidelore." + i)){
                    lores.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal(itemName + ".hidelore." + i));
                    i++;
                }
            }
        else
            lores.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("gui.inventory.shiftfordetail"));
    }
}
