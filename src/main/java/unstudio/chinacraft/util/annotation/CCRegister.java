package unstudio.chinacraft.util.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CCRegister {
    /**
     * @return 物品或方块的名字
     */
    String value();
}