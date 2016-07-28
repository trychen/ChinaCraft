package unstudio.sinocraft.item.combat.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNightClothes extends ModelBiped {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;

    public ModelNightClothes(float expand) {
        super(expand, 0, 64, 64);

        Shape1 = new ModelRenderer(this, 32, 0);
        Shape1.addBox(0F, 0F, 0F, 8, 1, 8);
        Shape1.setRotationPoint(-4F, -8.2F, -4F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 32, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 4, 6);
        Shape2.setRotationPoint(3.2F, -8F, -2F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 32, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 3, 2);
        Shape3.setRotationPoint(3.2F, -8F, -4F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 32, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 4, 6);
        Shape4.setRotationPoint(-4.2F, -8F, -2F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 32, 0);
        Shape5.addBox(0F, 0F, 0F, 1, 3, 2);
        Shape5.setRotationPoint(-4.2F, -8F, -4F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 32, 0);
        Shape6.addBox(0F, 0F, 0F, 8, 3, 1);
        Shape6.setRotationPoint(-4F, -8F, -4.2F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 32, 0);
        Shape7.addBox(0F, 0F, 0F, 8, 5, 1);
        Shape7.setRotationPoint(-4F, -8F, 3.2F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 32, 0);
        Shape8.addBox(0F, 0F, 0F, 8, 1, 1);
        Shape8.setRotationPoint(-4F, -3F, -4.2F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 32, 0);
        Shape9.addBox(0F, 0F, 0F, 8, 1, 1);
        Shape9.setRotationPoint(-4F, -2F, -4.2F);
        Shape9.setTextureSize(64, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 32, 0);
        Shape10.addBox(0F, 0F, 0F, 6, 1, 1);
        Shape10.setRotationPoint(-3F, -1F, -4.2F);
        Shape10.setTextureSize(64, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 32, 0);
        Shape11.addBox(0F, 0F, 0F, 4, 1, 3);
        Shape11.setRotationPoint(-2F, -0.8F, -4.2F);
        Shape11.setTextureSize(64, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 32, 0);
        Shape12.addBox(0F, 0F, 0F, 1, 1, 4);
        Shape12.setRotationPoint(3.2F, -3F, -4.2F);
        Shape12.setTextureSize(64, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 32, 0);
        Shape13.addBox(0F, 0F, 0F, 1, 1, 4);
        Shape13.setRotationPoint(-4.2F, -3F, -4.2F);
        Shape13.setTextureSize(64, 32);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        this.bipedHead.addChild(Shape1);
        this.bipedHead.addChild(Shape2);
        this.bipedHead.addChild(Shape3);
        this.bipedHead.addChild(Shape4);
        this.bipedHead.addChild(Shape5);
        this.bipedHead.addChild(Shape6);
        this.bipedHead.addChild(Shape7);
        this.bipedHead.addChild(Shape8);
        this.bipedHead.addChild(Shape9);
        this.bipedHead.addChild(Shape10);
        this.bipedHead.addChild(Shape11);
        this.bipedHead.addChild(Shape12);
        this.bipedHead.addChild(Shape13);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
