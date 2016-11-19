package unstudio.chinacraft.item;

import java.util.List;

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
import unstudio.chinacraft.tileentity.TileSericultureFrame;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSilkworm extends Item {

    public static final String[] name = new String[] {"graine", "young", "chrysalis"};
    public static final int[] lifeSpan = new int[] {16000, 45000, 9000};
    // For debugging: public static final int[] lifeSpan = new int[] {200, 200, 200};
    private IIcon[] icon;

    public ItemSilkworm() {
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(ChinaCraft.tabFarming);
        setUnlocalizedName("silkworm");
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if (!FeatureConfig.enableAdvancedSericulture)
            return super.showDurabilityBar(stack);
        else {
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Schedule"))
                if (stack.getTagCompound().getInteger("Schedule") > 0)
                    return true;
            return false;    
        }
    }
    
    @Override
    public int getDisplayDamage(ItemStack stack) {
        if (!FeatureConfig.enableAdvancedSericulture)
            return super.getDisplayDamage(stack);
        else {
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Schedule"))
                return (int) (1.0F * stack.getTagCompound().getInteger("Schedule") / lifeSpan[stack.getItemDamage()] * stack.getMaxDamage());          
            return 0;
        }
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.addInformation(stack, player, list, p_77624_4_);
        if (FeatureConfig.enableAdvancedSericulture && stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("Schedule"))
                list.add(EnumChatFormatting.GREEN + I18n.format("tooltip.progress.info") + (1 + getDisplayDamage(stack)) + "%");
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
        int j = MathHelper.clamp_int(p_77617_1_, 0, 2);
        return this.icon[j];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        icon = new IIcon[name.length];
        for (int i = 0; i < name.length; ++i) {
            this.icon[i] = p_94581_1_.registerIcon("chinacraft:silkworm_" + name[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 2);
        return super.getUnlocalizedName() + "_" + name[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < name.length; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }
}
