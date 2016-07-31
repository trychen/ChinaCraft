package unstudio.chinacraft.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.api.EntityMethod;

import java.util.List;

/**
 * Created by trychen on 16/7/31.
 */
public class ItemBuddhistCane extends ItemCCStaff {
    public ItemBuddhistCane() {
        setUnlocalizedName("buddhist_cane");
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if ((Minecraft.getSystemTime()&1)==0) return;
        List<EntityMob> mob = EntityMethod.findNear(player, EntityMob.class,5,5);
        if (mob.size() == 0){
            hasAnimal(EntityMethod.findNear(player, EntityAnimal.class,5,5));

        }
        for (int i = 0; i < 7; i++) {
            double x = player.posX + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 6 : -6);
            double y = player.posY + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 1 : -1);
            double z = player.posZ + player.worldObj.rand.nextDouble() * (player.worldObj.rand.nextBoolean() ? 6 : -6);
            player.worldObj.spawnParticle("happyVillager", x, y, z, 0, 0, 0);
        }
    }

    public int hasAnimal(List<EntityAnimal> animals){
        if (((short)Minecraft.getSystemTime())%16==0)
        for (EntityAnimal animal : animals) {
            if (animal.getHealth() != animal.getMaxHealth()){
                return 0;
            }
        }
        int count = animals.size() > 6?6:animals.size();
        int add = 0;
        for (EntityAnimal animal : animals) {
            if (animal.getHealth() != animal.getMaxHealth()){

            }
        }
        return add;
    }
}
