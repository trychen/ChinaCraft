package unstudio.chinacraft.util.annotation.register;

import java.lang.annotation.*;

/**
 * Created by trychen on 16/7/2.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 该注释用于半砖的注册,半砖的两个对象需要放在同一个类下,否则无法注册
 */
public @interface CCSlabRegister {
    /**
     * @return 方块名
     */
    String name();

    /**
     * @return 当个半砖对象名
     */
    String first();

    /**
     * @return 双层半砖对象名
     */
    String second();
}
