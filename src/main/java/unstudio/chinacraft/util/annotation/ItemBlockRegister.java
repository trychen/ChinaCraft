package unstudio.chinacraft.util.annotation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.Recipes;
import unstudio.chinacraft.item.CCCropPlantItem;
import unstudio.chinacraft.item.ItemCCSlab;
import unstudio.chinacraft.util.annotation.register.CorpPlant;
import unstudio.chinacraft.util.annotation.register.ICollection;
import unstudio.chinacraft.util.annotation.register.Register;
import unstudio.chinacraft.util.annotation.register.SlabRegister;

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
     * @see SlabRegister
     * @param c 类
     */
    public static void register(Class c) {
        for (Field f : c.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getDeclaredAnnotations().length > 0) {
                Object o;
                try {
                    o = f.get(null);
                } catch (IllegalAccessException e) {
                    ChinaCraft.log.error("Can't register non-public field as a Block/Item");
                    e.printStackTrace();
                    continue;
                } catch (NullPointerException e) {
                    ChinaCraft.log.error("Can't register non-static field as a Block/Item");
                    e.printStackTrace();
                    continue;
                }
                if (f.isAnnotationPresent(Register.class) || f.isAnnotationPresent(SlabRegister.class)) {

                    String name = null;
                    String ore = null;
                    Class<? extends ItemBlock> itemClass = ItemBlock.class;
                    if (f.isAnnotationPresent(Register.class)) {
                        Register anno = f.getAnnotation(Register.class);
                        name = anno.value();
                        itemClass = anno.itemClass();
                        ore = anno.ore();
                    }

                    if (o instanceof Block) {
                        if (f.isAnnotationPresent(SlabRegister.class)){
                            SlabRegister ann = f.getAnnotation(SlabRegister.class);
                            name = ann.name();
                            try {
                                GameRegistry.registerBlock((Block) o, ItemCCSlab.class,name,(Block) c.getField(ann.first()).get(null),(Block) c.getField(ann.second()).get(null),ann.second().equalsIgnoreCase(f.getName()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //以方块的形式注册
                            GameRegistry.registerBlock((Block) o, itemClass, name);
                        }
                        if (ore != null&&!ore.isEmpty()) OreDictionary.registerOre(ore, (Block) o);
                    } else if (o instanceof Item) {

                        if (f.isAnnotationPresent(CorpPlant.class)){
                            if (o instanceof CCCropPlantItem) {
                                CorpPlant ann = f.getAnnotation(CorpPlant.class);
                                try {
                                    Object obj = c.getField(ann.value()).get(null);
                                    if (obj instanceof Block) {
                                        ((CCCropPlantItem) o).setPlantBlock((Block) obj);
                                    } else {
                                        new IllegalArgumentException(f.getName() + "'s @CorpPlant.value() refers to an none-block field").printStackTrace();
                                    }
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                new IllegalArgumentException(f.getName() + " is not a CCCropPlantItem").printStackTrace();
                            }
                        }

                        //以物品的形式注册
                        GameRegistry.registerItem((Item) o, name);
                        if (ore != null&&!ore.isEmpty()) OreDictionary.registerOre(ore, (Item) o);
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
