package unstudio.chinacraft.util.annotation;

import unstudio.chinacraft.common.Recipes;
import unstudio.chinacraft.util.annotation.register.Register;

import java.util.List;

public class AnnotationInvoker {
    /**
     * 开始注册物品方块
     */
    public static void invoke(){
        // 获取物品集
        List<Class> itemBlockCollections = AnnotationClassGetter.getAllClassByInterface(ItemBlockCollection.class);

        // 获取需要执行Recipes方法的类
        List<Class> recipesableCollections = AnnotationClassGetter.getAllClassByInterface(Recipes.RecipeAble.class);

        // 开始执行
        for (Class clazz : itemBlockCollections) {
            System.out.println("Find ItemBlock Collections: " + clazz.getName());
            Register.register(clazz);
        }

        for (Class clazz : recipesableCollections) {
            Register.recipes(clazz);
        }
    }
}
