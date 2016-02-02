package unstudio.chinacraft.block.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * Created by trych on 2016/2/2.
 */
public class TileCCLamp extends TileModelBlock{
    /**
     * @param model   模型
     * @param texture 材质名，不需要加modid
     */
    private boolean opened = true;
    public TileCCLamp(ModelBase model, String texture) {
        super(model, texture);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("opened",opened);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        opened = p_145839_1_.getBoolean("opened");
    }

    @Override
    public String getTexture() {
        return opened?super.getTexture() + "_on":super.getTexture() + "_off";
    }

    public boolean isOpened() {
        return opened;
    }
    public void turn(){
        if (opened) opened= false;
        else opened = true;
    }
}
