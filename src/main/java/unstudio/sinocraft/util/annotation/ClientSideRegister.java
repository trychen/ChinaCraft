package unstudio.sinocraft.util.annotation;

import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.util.annotation.register.IClient;
import unstudio.sinocraft.util.annotation.register.ICollection;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by trychen on 16/7/28.
 */
public class ClientSideRegister {
    public static void registerAll(){
        // 获取ClientInit集
        List<Class> client = AnnotationClassGetter.getAllClassByInterface(ICollection.class);

        // 开始执行
        for (Class clazz : client) {
            for (Field field : clazz.getFields()) {
                try {
                    Object object = field.get(null);
                    if (object instanceof IClient){
                        ((IClient) object).clientInit();
                    }
                } catch (IllegalAccessException e) {
                    SinoCraft.log.error("Can't register non-static field as a Client Side Register");
                    e.printStackTrace();
                } catch (NullPointerException e){
                    SinoCraft.log.error("Can't register non-static field as a Client Side Register");
                    e.printStackTrace();
                }
            }
        }
    }
}
