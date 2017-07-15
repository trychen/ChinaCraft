package unstudio.chinacraft.util.annotation.register;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CorpPlant {
    /**
     * BlockCrops的名字，要求在同一个类中，且public static
     */
    String value();
}
