package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

public class CCShield extends Item  implements ISpecialEquippedRender {
    @Override
    public void doRender() {
    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.shield;
    }

    /**
     * 获取最终受到的伤害
     * @param stack 物品
     * @param attacker 攻击者
     * @param amount 伤害
     */
    public float defense(ItemStack stack, DamageSource attacker, float amount) {
        return amount;
    }
}
