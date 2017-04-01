package unstudio.chinacraft.client.model.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by trychen on 17/4/1.
 */
public class ModelRendererScale extends ModelRenderer {
    private float scale;
    public ModelRendererScale(ModelBase p_i1172_1_, String p_i1172_2_) {
        super(p_i1172_1_, p_i1172_2_);
        scale = 1f;
    }
    public ModelRendererScale(ModelBase p_i1172_1_, String p_i1172_2_, float scale) {
        super(p_i1172_1_, p_i1172_2_);
        this.scale = scale;
    }

    public ModelRendererScale(ModelBase p_i1173_1_) {
        super(p_i1173_1_);
        this.scale = 1f;
    }

    public ModelRendererScale(ModelBase p_i1173_1_, float scale) {
        super(p_i1173_1_);
        this.scale = scale;
    }

    public ModelRendererScale(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_) {
        super(p_i1174_1_, p_i1174_2_, p_i1174_3_);
        this.scale = 1f;
    }

    public ModelRendererScale(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_, float scale) {
        super(p_i1174_1_, p_i1174_2_, p_i1174_3_);
        this.scale = scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public void render(float p_78785_1_) {
        GL11.glScalef(scale, scale, scale);
        super.render(p_78785_1_);
    }
}
