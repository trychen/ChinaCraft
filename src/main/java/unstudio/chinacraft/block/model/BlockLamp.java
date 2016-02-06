package unstudio.chinacraft.block.model;

import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import unstudio.chinacraft.tileentity.TileCCLamp;

/**
 * Created by trych on 2016/2/2.
 */
public class BlockLamp extends CCModelBlock {
    /**
     * @param material
     *            材质
     * @param model
     *            模型
     * @param name
     *            名字
     */
    public BlockLamp(Material material, ModelBase model, String name) {
        super(material, model, name);
        setLightLevel(5.0f);

    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileCCLamp(this.getModel(), getTextureName());
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p_149727_6_,
            float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileCCLamp tileEntity = (TileCCLamp) world.getTileEntity(x, y, z);
        if (tileEntity == null)
            return false;
        if (tileEntity.turn()) {
            setLightLevel(5.0f);
        } else
            setLightLevel(0);
        return true;
    }
}
