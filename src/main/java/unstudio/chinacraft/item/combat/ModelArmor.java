package unstudio.chinacraft.item.combat;

import java.io.File;
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelArmor extends ItemArmor {
    @SideOnly(Side.CLIENT)
    protected IIcon itemIcon;
    private String TextureName = "";
    private ModelBiped armorModel;
    private int textureType;

    public ModelArmor(ArmorMaterial armorMaterial, String name, String textureName, int type, int render_idx) {
        this(armorMaterial, name, textureName, 0, type, render_idx);
    }

    public ModelArmor(ArmorMaterial armorMaterial, String name, String textureName, int textureType, int type,
            int render_idx) {
        super(armorMaterial, render_idx, type);
        setUnlocalizedName(name);
        TextureName = textureName;
        this.textureType = textureType;
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) {
        if (armorModel != null) {
            armorModel.bipedHead.showModel = armorSlot == 0;
            armorModel.bipedHeadwear.showModel = false;
            armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            armorModel.bipedRightArm.showModel = armorSlot == 1;
            armorModel.bipedLeftArm.showModel = armorSlot == 1;
            armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

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
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("chinacraft:" + getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return this.itemIcon;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        if (textureType == 0) {
            return String.format("chinacraft:textures" + File.separator + "models" + File.separator + "armor"
                    + File.separator + "%s.png", TextureName);
        }
        return String.format("chinacraft:textures" + File.separator + "models" + File.separator + "armor"
                + File.separator + "%s_layer_%d.png", TextureName, slot == 2 ? 2 : 1);
    }

    public void setArmorModel(ModelBiped armorModel) {
        this.armorModel = armorModel;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (StatCollector.canTranslate("item." + TextureName + ".lore")) {
            p_77624_3_.add(StatCollector.translateToLocal("item." + TextureName + ".lore"));
        } else {
            int i = 0;
            while (true) {
                i++;
                if (StatCollector.canTranslate("item." + TextureName + ".lore." + i)) {
                    p_77624_3_.add(StatCollector.translateToLocal("item." + TextureName + ".lore." + i));
                } else {
                    break;
                }
            }
            super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        }
    }
}
