package unstudio.sinocraft.util.annotation.register;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OreRegister {
    /**
     * @return 物品或方块的名字
     */
    String name();

    /**
     * @return 矿物字典名
     */
    String ore();
}
