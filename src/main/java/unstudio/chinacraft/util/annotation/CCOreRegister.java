package unstudio.chinacraft.util.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CCOreRegister{
    String name();
    String ore();
}
