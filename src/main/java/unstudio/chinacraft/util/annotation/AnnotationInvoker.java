package unstudio.chinacraft.util.annotation;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AnnotationInvoker {
    public static Set<Class> fieldClasses = new HashSet<>();

    /**
     * 用于注册物品方块集合类
     * @param c 物品类
     */
    public static void register(Class c){
        fieldClasses.add(c);
    }


    /**
     * 开始注册物品方块
     */
    public static void invoke(){
        for (Class c : fieldClasses) {
            for (Field f : c.getFields()) {
                if (f.isAnnotationPresent(CCRegister.class) || f.isAnnotationPresent(CCOreRegister.class)) {
                    Object o;

                    try {
                        o = f.get(null);
                    } catch (IllegalAccessException e) {
                        System.err.println("Can't register non-public field as a Block/Item");
                        e.printStackTrace();
                        continue;
                    } catch (NullPointerException e){
                        System.err.println("Can't register non-static field as a Block/Item");
                        e.printStackTrace();
                        continue;
                    }

                    String name = null;
                    String ore = null;
                    if (f.isAnnotationPresent(CCRegister.class)){
                        name = f.getAnnotation(CCRegister.class).value();
                    } else if (f.isAnnotationPresent(CCOreRegister.class)){
                        CCOreRegister ann = f.getAnnotation(CCOreRegister.class);
                        name = ann.name();
                        ore = ann.ore();
                    }

                    if (o instanceof Block){
                        GameRegistry.registerBlock((Block) o,name);
                        if (ore != null) OreDictionary.registerOre(ore,(Block) o);
                    } else if (o instanceof Item){
                        GameRegistry.registerItem((Item) o,name);
                        if (ore != null) OreDictionary.registerOre(ore,(Item) o);
                    } else {
                        new IllegalArgumentException("Can't register field which haven't extended Block").printStackTrace();
                        continue;
                    }
                }
            }
        }
    }
}
