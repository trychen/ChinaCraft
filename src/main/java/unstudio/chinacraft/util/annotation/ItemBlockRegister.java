package unstudio.sinocraft.util.annotation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.common.Recipes;
import unstudio.sinocraft.util.annotation.register.ICollection;
import unstudio.sinocraft.util.annotation.register.OreRegister;
import unstudio.sinocraft.util.annotation.register.Register;
import unstudio.sinocraft.util.annotation.register.SlabRegister;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by trychen on 16/7/9.
 */
public class ItemBlockRegister {
    public static void registerAll(){
        // 获取物品集
        List<Class> itemBlockCollections = AnnotationClassGetter.getAllClassByInterface(ICollection.class);

        // 获取需要执行Recipes方法的类
        List<Class> recipesableCollections = AnnotationClassGetter.getAllClassByInterface(Recipes.RecipeAble.class);

        // 开始执行
        for (Class clazz : itemBlockCollections) {
            ItemBlockRegister.register(clazz);
        }

        for (Class clazz : recipesableCollections) {
            ItemBlockRegister.recipes(clazz);
        }
    }

    /**
     * 该方法用于对使用了CC注释注册系统的类进行注册,支持以下的注释
     * @see Register
     * @see OreRegister
     * @see SlabRegister
     * @param c 类
     */
    public static void register(Class c) {
        for (Field f : c.getFields()) {
            if (f.getDeclaredAnnotations().length > 0) {
                Object o;
                try {
                    o = f.get(null);
                } catch (IllegalAccessException e) {
                    SinoCraft.log.error("Can't register non-public field as a Block/Item");
                    e.printStackTrace();
                    continue;
                } catch (NullPointerException e) {
                    SinoCraft.log.error("Can't register non-static field as a Block/Item");
                    e.printStackTrace();
                    continue;
                }
                if (f.isAnnotationPresent(Register.class) || f.isAnnotationPresent(OreRegister.class)) {

                    String name = null;
                    String ore = null;
                    if (f.isAnnotationPresent(Register.class)) {
                        name = f.getAnnotation(Register.class).value();
                    } else if (f.isAnnotationPresent(OreRegister.class)) {
                        OreRegister ann = f.getAnnotation(OreRegister.class);
                        name = ann.name();
                        ore = ann.ore();
                    }

                    if (o instanceof Block) {
                        //以方块的形式注册
                        GameRegistry.registerBlock((Block) o, name);
                        if (ore != null) OreDictionary.registerOre(ore, (Block) o);
                    } else if (o instanceof Item) {
                        //以物品的形式注册
                        GameRegistry.registerItem((Item) o, name);
                        if (ore != null) OreDictionary.registerOre(ore, (Item) o);
                    } else {
                        //非可注册的物品
                        new IllegalArgumentException("Can't register field which haven't extended Block").printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 该方法可以对拥有静态Public的recipes方法进行执行,使用以下接口即可自动执行
     * @see Recipes.RecipeAble
     * @param c 实现recipes方法的类
     */
    public static void recipes(Class c) {
        try {
            c.getMethod("recipes").invoke(null);
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
