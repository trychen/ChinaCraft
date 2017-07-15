package unstudio.chinacraft.item.combat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.ItemLoreHelper;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

import java.util.List;

/**
 * Created by trychen on 17/5/28.
 */
public class CCJointStaff extends ItemSword implements ISpecialEquippedRender{
    public CCJointStaff() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("joint_staff");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemLoreHelper.shiftLoreWithStat(p_77624_3_,getUnlocalizedName());
    }

    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.staff;
    }
}
