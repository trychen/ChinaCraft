package unstudio.chinacraft.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by trych on 2016/2/2.
 */
public class TileCCLamp extends TileModelBlock {
    /**
     * @param model
     *            模型
     * @param texture
     *            材质名，不需要加modid
     */
    private boolean opened = true;

    public TileCCLamp(Class <? extends ModelBase> model, String texture) {
        super(model, texture);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("opened", opened);
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        opened = p_145839_1_.getBoolean("opened");
    }

    @Override
    public String getTexture() {
        return opened ? super.getTexture() + "_on" : super.getTexture() + "_off";
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean turn() {
        if (opened) {
            opened = false;
            return false;
        } else {
            opened = true;
            return true;
        }
    }
}
