package unstudio.chinacraft.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 16/7/28.
 */
public class KeyMessageHandler implements IMessageHandler<KeyMessage, IMessage> {
    @Override
    public IMessage onMessage(KeyMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        switch(message.key){
            case KeyMessage.KEY_DOUBLE_JUMP:
                NBTTagCompound tCompound = player.getEntityData();
                if(tCompound.hasKey("chinacraft.nightClothesHasJumped")) {
                    if (player.motionY < 0.4 && tCompound.getInteger("chinacraft.nightClothesHasJumped") < 2) {
                        player.jump();
                        player.motionX *= 1.3;
                        player.motionY += 0.15;
                        player.motionZ *= 1.3;
                        player.velocityChanged = true;
                    }
                }
                break;
            case KeyMessage.KEY_ATTACK:
                if (!player.canAttackWithItem() || player.isUsingItem() || player.isBlocking() || player.getHeldItem() == null || player.getHeldItem().getItem() != ChinaCraft.jointStaff) break;
                DamageSource damageSource = DamageSource.causeMobDamage(player).setMagicDamage();
                int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, player.getHeldItem());
                int fireLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, player.getHeldItem());
                int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, player.getHeldItem());
                int damage = 1;
                for (EntityLiving entityLiving : EntityMethod.findNear(player, EntityLiving.class, 2, 2)) {
                    if (player.equals(entityLiving)) continue;
                    if (!entityLiving.isImmuneToFire())
                        entityLiving.setFire(3 * fireLevel);
                    entityLiving.attackEntityFrom(damageSource, 3 + 1.5f*powerLevel);
                    damage++;
                }
                if (unbreakingLevel <= 0 || player.worldObj.rand.nextInt(unbreakingLevel) == 0){
                    player.getHeldItem().damageItem(damage ,player);
                }
                break;
        }
        return null;
    }
}
