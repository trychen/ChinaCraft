package unstudio.chinacraft.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by trych on 2016/1/28.
 */
public class TileModelBlock extends TileEntity {
    private ResourceLocation resourceLocation;
    private ModelBase model;

    public TileModelBlock(Class<? extends ModelBase> modelClass, String texture) {
        if (texture != null)
            resourceLocation = new ResourceLocation("chinacraft:textures/models/block/" + texture + ".png");
        try {
            model = modelClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public ModelBase getModel() {
        return model;
    }

    public ResourceLocation getTexture() {
        return resourceLocation;
    }
}
