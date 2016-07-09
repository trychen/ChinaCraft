package unstudio.chinacraft.util.annotation;

import unstudio.chinacraft.common.Recipes;
import unstudio.chinacraft.util.annotation.register.Register;

import java.util.ArrayList;
import java.util.List;

public class AnnotationInvoker {
    /**
     * 开始注册物品方块
     */
    public static void invoke(){
        // 获取物品集
        List<Class> itemBlockCollections = ClassGetter.getAllClassByInterface(ItemBlockCollection.class);
        // 获取需要执行Recipes方法的类
        List<Class> recipesableCollections = ClassGetter.getAllClassByInterface(Recipes.RecipeAble.class);

        // 开始执行
        Register.register(itemBlockCollections);
        Register.recipes(recipesableCollections);
    }
}
