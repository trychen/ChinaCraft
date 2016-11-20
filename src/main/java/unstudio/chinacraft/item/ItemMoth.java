package unstudio.chinacraft.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.config.FeatureConfig;

public class ItemMoth extends Item {
    
    public static final String[] moth_type = new String[] {"male", "female"};
    private IIcon[] moth_icon;

    public ItemMoth() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ChinaCraft.tabFarming);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.addInformation(stack, player, list, p_77624_4_);
        if (FeatureConfig.enableAdvancedSericulture) {
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Generation"))
                list.add(EnumChatFormatting.YELLOW + I18n.format("tooltip.generation.info") + stack.getTagCompound().getInteger("Generation"));
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Productivity"))
                list.add(EnumChatFormatting.GOLD + I18n.format("tooltip.productivity.info") + stack.getTagCompound().getInteger("Productivity"));
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Speed"))
                list.add(EnumChatFormatting.GOLD + I18n.format("tooltip.speed.info") + stack.getTagCompound().getInteger("Speed"));
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Fertility"))
                list.add(EnumChatFormatting.GOLD + I18n.format("tooltip.fertility.info") + stack.getTagCompound().getInteger("Fertility"));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        int j = MathHelper.clamp_int(p_77617_1_, 0, moth_type.length - 1);
        return this.moth_icon[j];
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, moth_type.length - 1);
        return super.getUnlocalizedName() + "." + moth_type[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < moth_type.length; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        this.moth_icon = new IIcon[moth_type.length];
        for (int i = 0; i < moth_type.length; ++i) {
            this.moth_icon[i] = p_94581_1_.registerIcon("chinacraft:moth_" + moth_type[i]);
        }
    }

}
