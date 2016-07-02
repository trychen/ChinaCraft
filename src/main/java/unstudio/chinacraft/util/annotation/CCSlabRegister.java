package unstudio.chinacraft.util.annotation;

import java.lang.annotation.*;

/**
 * Created by trychen on 16/7/2.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CCSlabRegister {
    String name();
    String first();
    String second();
}
