package unstudio.chinacraft.util.annotation;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.util.annotation.register.ICollection;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by trychen on 16/7/31.
 */
public class SpecialItemRenderRegister {
    public static void registerAll(){
        // 获取ClientInit集
        List<Class> client = AnnotationClassGetter.getAllClassByInterface(ICollection.class);

        //初始化渲染器
        SpecialItemRender s = new SpecialItemRender();

        // 开始执行
        for (Class clazz : client) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    Object object = field.get(null);
                    if (object instanceof ISpecialEquippedRender){
                        register((Item) object,s);
                    }
                } catch (IllegalAccessException e) {
                } catch (NullPointerException e){
                }
            }
        }
    }

    public static void register(Item i,SpecialItemRender s){
        MinecraftForgeClient.registerItemRenderer(i, s);
    }
}
