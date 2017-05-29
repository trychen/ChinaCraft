package unstudio.chinacraft.item.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import unstudio.chinacraft.client.render.item.SpecialItemRender;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.annotation.register.ISpecialEquippedRender;

/**
 * Created by trychen on 17/5/28.
 */
public class CCJointStaff extends ItemSword implements ISpecialEquippedRender{
    public CCJointStaff() {
        super(ToolMaterial.IRON);
        setCreativeTab(ChinaCraft.tabTool);
        setUnlocalizedName("joint_staff");
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    }



    @Override
    public void doRender() {

    }

    @Override
    public SpecialItemRender.RenderType getSpecialRenderType() {
        return SpecialItemRender.RenderType.staff;
    }
}
