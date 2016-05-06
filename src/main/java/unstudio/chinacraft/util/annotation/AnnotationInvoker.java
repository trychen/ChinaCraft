package unstudio.chinacraft.util.annotation;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.Sys;

import unstudio.chinacraft.common.Recipes;
import unstudio.chinacraft.common.Recipes.RecipeAble;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AnnotationInvoker {
    public static Set<Class> fieldClasses = new HashSet<Class>();
    public static Set<Recipes.RecipeAble> neededRecipes = new HashSet<RecipeAble>();

    /**
     * 鐢ㄤ簬娉ㄥ唽鐗╁搧鏂瑰潡闆嗗悎绫�
     * @param c 鐗╁搧绫�
     */
    public static void register(Class c){
        fieldClasses.add(c);
    }


    /**
     * 寮�濮嬫敞鍐岀墿鍝佹柟鍧�
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

                    //鎵ц鍚堟垚娉ㄥ唽
                    if (o instanceof Recipes.RecipeAble){
                        neededRecipes.add((Recipes.RecipeAble)o);
                    }

                    if (o instanceof Block){
                        //浠ユ柟鍧楃殑褰㈠紡娉ㄥ唽
                        GameRegistry.registerBlock((Block) o,name);
                        if (ore != null) OreDictionary.registerOre(ore,(Block) o);
                    } else if (o instanceof Item){
                        //浠ョ墿鍝佺殑褰㈠紡娉ㄥ唽
                        GameRegistry.registerItem((Item) o,name);
                        if (ore != null) OreDictionary.registerOre(ore,(Item) o);
                    } else {
                        //闈炲彲娉ㄥ唽鐨勭墿鍝�
                        new IllegalArgumentException("Can't register field which haven't extended Block").printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

    public static void invokeRecipe(){
        for (Recipes.RecipeAble neededRecipe : neededRecipes)
            neededRecipe.recipes();
    }

    public static void close(){
        fieldClasses = null;
        neededRecipes = null;

        System.gc();
    }
}
