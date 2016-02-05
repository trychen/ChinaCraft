package unstudio.chinacraft.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by trych on 2016/1/28.
 */
public class TileModelBlock extends TileEntity{
    private ModelBase model;
    private String texture;

    /**
     * @param model 模型
     * @param texture 材质名，不需要加modid
     */
    public TileModelBlock(ModelBase model, String texture) {
        this.model = model;
        this.texture = texture;
    }


    public ModelBase getModel() {
        return model;
    }

    public String getTexture() {
        return texture;
    }
}
