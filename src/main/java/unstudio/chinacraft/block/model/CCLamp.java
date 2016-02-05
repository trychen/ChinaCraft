package unstudio.chinacraft.block.model;


import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unstudio.chinacraft.tileentity.TileCCLamp;

/**
 * Created by trych on 2016/2/2.
 */
public class CCLamp extends CCModelBlock{
    /**
     * @param material 材质
     * @param model    模型
     * @param name     名字
     */
    public CCLamp(Material material, ModelBase model, String name) {
        super(material, model, name);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileCCLamp(this.getModel(),getTextureName());
    }
}
