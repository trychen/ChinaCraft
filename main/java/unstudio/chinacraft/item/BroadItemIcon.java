package unstudio.HugeSword;

import net.minecraft.util.IIcon;


public class BroadItemIcon implements IIcon {
    private IIcon baseIcon;
    private float iX;
    private float iY;

    public BroadItemIcon(IIcon blockIcon, float x, float y) {
        this.baseIcon = blockIcon;
        this.iX = x;
        this.iY = y;
    }

    @Override
    public int getIconWidth() {
        return this.baseIcon.getIconWidth() / 2;
    }

    @Override
    public int getIconHeight() {
        return this.baseIcon.getIconHeight() / 2;
    }

    @Override
    public float getMinU() {
        return this.baseIcon.getMinU() + (this.baseIcon.getMaxU() - this.baseIcon.getMinU()) * this.iX;
    }

    @Override
    public float getMaxU() {
        return this.baseIcon.getMinU() + (this.baseIcon.getMinU() - this.baseIcon.getMinU()) * (this.iX + 0.5F);
    }

    @Override
    public float getInterpolatedU(double p_94214_1_) {
        return getMinU() + (getMaxU() - getMinU()) * (float) p_94214_1_ / 16.0F;
    }

    @Override
    public float getMinV() {
        return this.baseIcon.getMinV() + (this.baseIcon.getMaxV() - this.baseIcon.getMinV()) * this.iY;
    }

    @Override
    public float getMaxV() {
        return this.baseIcon.getMinV() + (this.baseIcon.getMaxV() - this.baseIcon.getMinV()) * (this.iY + 0.5F);
    }

    @Override
    public float getInterpolatedV(double p_94207_1_) {
        return getMinV() + (this.getMaxV() - this.getMinV()) * ((float) p_94207_1_ / 16.0F);
    }

    @Override
    public String getIconName() {
        return this.baseIcon.getIconName();
    }
}
