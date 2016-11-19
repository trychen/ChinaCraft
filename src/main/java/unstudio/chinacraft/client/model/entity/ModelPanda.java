package unstudio.chinacraft.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPanda extends ModelBase {
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer horn1;
    ModelRenderer horn2;

    public ModelPanda() {
        textureWidth = 128;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 83, 0);
        Shape1.addBox(0F, 0F, 0F, 6, 4, 4);
        Shape1.setRotationPoint(-3F, 15F, -18F);
        Shape1.setTextureSize(128, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0.4712389F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 4, 4, 2);
        Shape2.setRotationPoint(-2F, 10F, 9F);
        Shape2.setTextureSize(128, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0.418879F, 0F, 0F);
        head = new ModelRenderer(this, 16, 39);
        head.addBox(-4F, -4F, -6F, 8, 9, 13);
        head.setRotationPoint(0F, 12F, -9F);
        head.setTextureSize(128, 64);
        head.mirror = true;
        setRotation(head, 0.2617994F, 0F, 0F);
        body = new ModelRenderer(this, 18, 9);
        body.addBox(-6F, -10F, -7F, 12, 19, 10);
        body.setRotationPoint(0F, 11F, 2F);
        body.setTextureSize(128, 64);
        body.mirror = true;
        setRotation(body, 1.570796F, 0F, 0F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-3F, 0F, -2F, 4, 6, 4);
        leg1.setRotationPoint(-3F, 18F, 7F);
        leg1.setTextureSize(128, 64);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-1F, 0F, -2F, 4, 6, 4);
        leg2.setRotationPoint(3F, 18F, 7F);
        leg2.setTextureSize(128, 64);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-3F, 0F, -3F, 4, 6, 4);
        leg3.setRotationPoint(-3F, 18F, -4F);
        leg3.setTextureSize(128, 64);
        leg3.mirror = true;
        setRotation(leg3, 0F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-1F, 0F, -3F, 4, 6, 4);
        leg4.setRotationPoint(3F, 18F, -4F);
        leg4.setTextureSize(128, 64);
        leg4.mirror = true;
        setRotation(leg4, 0F, 0F, 0F);
        horn1 = new ModelRenderer(this, 22, 0);
        horn1.addBox(-4F, -5F, -4F, 3, 3, 1);
        horn1.setRotationPoint(0F, 12F, -12F);
        horn1.setTextureSize(128, 64);
        horn1.mirror = true;
        setRotation(horn1, 0F, 0.837758F, 0F);
        horn2 = new ModelRenderer(this, 30, 0);
        horn2.addBox(3F, -5F, -4F, 3, 3, 1);
        horn2.setRotationPoint(-2F, 12F, -13F);
        horn2.setTextureSize(128, 64);
        horn2.mirror = true;
        setRotation(horn2, 0F, -0.837758F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        head.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        horn1.render(f5);
        horn2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleY = y;
        model.rotateAngleX = x;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
    }
}
