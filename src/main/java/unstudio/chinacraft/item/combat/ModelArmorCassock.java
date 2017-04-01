package unstudio.chinacraft.item.combat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.client.model.armor.ModelCassock;

/**
 * Created by trychen on 17/3/25.
 */
public class ModelArmorCassock extends ModelArmor {
    @SideOnly(Side.CLIENT)
    private ModelCassock armorModel = new ModelCassock();

    public ModelArmorCassock() {
        super(ArmorMaterial.CLOTH, "cassock", null, 1, 1, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorModel != null) {
            armorModel = new ModelCassock();
            armorModel.bipedBody.showModel = true;
            armorModel.bipedRightArm.showModel = true;
            armorModel.bipedLeftArm.showModel = true;
            armorModel.bipedRightLeg.showModel = true;
            armorModel.bipedLeftLeg.showModel = true;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();

            armorModel.heldItemRight = 0;
            armorModel.aimedBow = false;

            EntityPlayer player = (EntityPlayer) entityLiving;

            ItemStack held_item = player.getEquipmentInSlot(0);

            if (held_item != null) {
                armorModel.heldItemRight = 1;

                if (player.getItemInUseCount() > 0) {

                    EnumAction enumaction = held_item.getItemUseAction();

                    if (enumaction == EnumAction.bow) {
                        armorModel.aimedBow = true;
                    } else if (enumaction == EnumAction.block) {
                        armorModel.heldItemRight = 3;
                    }


                }

            }


        }


        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        return "chinacraft:textures/models/armor/cassock.png";
    }
}
