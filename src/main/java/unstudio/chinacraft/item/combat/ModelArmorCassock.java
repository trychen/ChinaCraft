package unstudio.chinacraft.item.combat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.client.model.armor.ModelCassock;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 17/3/25.
 */
public class ModelArmorCassock extends ModelArmor {
    @SideOnly(Side.CLIENT)
    private Object armorModel;

    private static Integer itemId;

    public ModelArmorCassock() {
        super(ArmorMaterial.CLOTH, "cassock", "cassock", 1, 1, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorModel == null) armorModel = new ModelCassock();
        ModelCassock model = (ModelCassock) armorModel;
        model = new ModelCassock();
        model.bipedBody.showModel = true;
        model.bipedRightArm.showModel = true;
        model.bipedLeftArm.showModel = true;
        model.bipedRightLeg.showModel = true;
        model.bipedLeftLeg.showModel = true;

        model.isSneak = entityLiving.isSneaking();
        model.isRiding = entityLiving.isRiding();
        model.isChild = entityLiving.isChild();

        model.heldItemRight = 0;
        model.aimedBow = false;

        EntityPlayer player = (EntityPlayer) entityLiving;

        ItemStack held_item = player.getEquipmentInSlot(0);

        if (held_item != null) {
            model.heldItemRight = 1;
            if (player.getItemInUseCount() > 0) {
                EnumAction enumaction = held_item.getItemUseAction();
                if (enumaction == EnumAction.bow) {
                    model.aimedBow = true;
                } else if (enumaction == EnumAction.block) {
                    model.heldItemRight = 3;
                }
            }
        }
        return model;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        return "chinacraft:textures/models/armor/cassock.png";
    }

    public static int getItemId() {
        if (itemId == null) {
            itemId = Item.getIdFromItem(ChinaCraft.cassock);
        }
        return itemId;
    }
}
