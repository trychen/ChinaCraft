package unstudio.chinacraft.util.annotation.register;

import net.minecraft.item.ItemBlock;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Register {
    /**
     * @return 物品或方块的名字
     */
    String value();

    /**
     * @return 物品Class,默认构造方法只能有一个Block参数
     */
    Class<? extends ItemBlock> itemClass() default ItemBlock.class;


    /**
     * @return 矿物词典
     */
    String ore() default "";
}