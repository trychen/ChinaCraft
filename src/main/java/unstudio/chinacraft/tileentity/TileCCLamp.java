package unstudio.chinacraft.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * Created by trych on 2016/2/2.
 */
public class TileCCLamp extends TileModelBlock {
    /**
     * @param model
     * 模型
     * @param texture
     * 材质名，不需要加modid
     */
    private boolean opened = true;
    private boolean switchAble = false;

    public TileCCLamp(Class<? extends ModelBase> model, String textureName, boolean switchAble) {
        super(model, null);
        this.switchAble = switchAble;
        if (switchAble) {
            on = new ResourceLocation("chinacraft:textures/models/block/" + textureName + "_on.png");
            off = new ResourceLocation("chinacraft:textures/models/block/" + textureName + "_off.png");
        } else on = new ResourceLocation("chinacraft:textures/models/block/" + textureName + ".png");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("opened", opened);
        nbtTagCompound.setBoolean("switchAble", switchAble);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        opened = p_145839_1_.getBoolean("opened");
        switchAble = p_145839_1_.getBoolean("switchAble");
    }

    private ResourceLocation on, off;

    @Override
    public ResourceLocation getTexture() {
        return switchAble ? (opened ? on : off) : on;
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
