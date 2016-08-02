package unstudio.chinacraft.item.combat;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.List;

public class ModelArmor extends ItemArmor {
//    @SideOnly(Side.CLIENT)
//    protected IIcon itemIcon;
    private String textureName = "";
    private ModelBiped armorModel;
    private int textureType;

    public ModelArmor(ArmorMaterial armorMaterial, String name, String textureName, int textureType, EntityEquipmentSlot type,
            int render_idx) {
        super(armorMaterial, render_idx, type);
        setUnlocalizedName(name);
        this.textureName = textureName;
        this.textureType = textureType;
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
    }

    @Override
    public ArmorMaterial getArmorMaterial() {
        return super.getArmorMaterial();
    }
    /*
        TODO 1.8+ New Texture System
        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(IIconRegister iconRegister) {
            this.itemIcon = iconRegister.registerIcon("chinacraft:" + getUnlocalizedName().substring(5));
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IIcon getIcon(ItemStack stack, int pass) {
            return this.itemIcon;
        }
    */

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (armorModel != null) {
            armorModel.bipedHead.showModel = armorSlot.equals(EntityEquipmentSlot.HEAD);
            armorModel.bipedHeadwear.showModel = false;
            armorModel.bipedBody.showModel = armorSlot.equals(EntityEquipmentSlot.CHEST);
            armorModel.bipedRightArm.showModel = armorSlot.equals(EntityEquipmentSlot.MAINHAND);
            armorModel.bipedLeftArm.showModel = armorSlot.equals(EntityEquipmentSlot.OFFHAND);
            armorModel.bipedRightLeg.showModel = armorSlot.equals(EntityEquipmentSlot.LEGS);
            armorModel.bipedLeftLeg.showModel = armorSlot.equals(EntityEquipmentSlot.LEGS);

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();

            //armorModel.heldItemRight = 0;
            //armorModel.aimedBow = false;

            EntityPlayer player = (EntityPlayer) entityLiving;

            ItemStack held_item = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

            if (held_item != null) {
                //armorModel.heldItemRight = 1;

                if (player.getItemInUseCount() > 0) {

                    EnumAction enumaction = held_item.getItemUseAction();

                    if (enumaction == EnumAction.BOW) {
                        //armorModel.aimedBow = true;
                    } else if (enumaction == EnumAction.BLOCK) {
                        //armorModel.heldItemRight = 3;
                    }
                }
            }
        }

        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (textureType == 0) {
            return String.format("chinacraft:textures/models/armor/%s.png", textureName);
        }
        return String.format("chinacraft:textures/models/armor/%s_layer_%d.png", textureName, slot == EntityEquipmentSlot.LEGS ? 2 : 1);
    }

    public void setArmorModel(ModelBiped armorModel) {
        this.armorModel = armorModel;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (I18n.hasKey("item." + textureName + ".lore")) {
            p_77624_3_.add(I18n.format("item." + textureName + ".lore"));
        } else {
            int i = 0;
            while (true) {
                i++;
                if (I18n.hasKey("item." + textureName + ".lore." + i)) {
                    p_77624_3_.add(I18n.format("item." + textureName + ".lore." + i));
                } else {
                    break;
                }
            }
            super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        }
    }
}
