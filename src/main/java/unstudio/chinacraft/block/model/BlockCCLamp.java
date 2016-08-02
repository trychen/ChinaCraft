package unstudio.chinacraft.block.model;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import unstudio.chinacraft.tileentity.TileCCLamp;

import javax.annotation.Nullable;

public class BlockCCLamp extends BlockCCModel {
    /**
     * @param material
     *           材质
     * @param model
     *            模型
     * @param name
     *            名字
     */
    public BlockCCLamp(Material material, Class <? extends ModelBase> model, String name) {
        super(material, model, name);
        setLightLevel(5.0f);

    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCCLamp(this.getModel(), getTextureName());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileCCLamp tileEntity = (TileCCLamp) worldIn.getTileEntity(pos);
        if (tileEntity == null)
            return false;
        if (tileEntity.turn()) setLightLevel(5.0f);
        else setLightLevel(0);
        return true;
    }
}
