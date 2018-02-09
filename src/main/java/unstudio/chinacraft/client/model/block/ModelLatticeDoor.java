package unstudio.chinacraft.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by trychen on 17/7/17.
 */
public class ModelLatticeDoor extends ModelBase {
    //fields
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
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape13;
    ModelRenderer Shape20;

    public ModelLatticeDoor() {
        textureWidth = 256;
        textureHeight = 256;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 14, 2, 2);
        Shape1.setRotationPoint(-6F, 21F, -7F);
        Shape1.setTextureSize(256, 256);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, -1.570796F, 0F);
        Shape2 = new ModelRenderer(this, 0, 6);
        Shape2.addBox(0F, 0F, 0F, 1, 47, 2);
        Shape2.setRotationPoint(-6F, -24F, -8F);
        Shape2.setTextureSize(256, 256);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, -1.570796F, 0F);
        Shape3 = new ModelRenderer(this, 7, 6);
        Shape3.addBox(0F, 0F, -2F, 1, 47, 2);
        Shape3.setRotationPoint(-7.9F, -24F, 7F);
        Shape3.setTextureSize(256, 256);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, -1.58825F, 0F);
        Shape4 = new ModelRenderer(this, 34, 0);
        Shape4.addBox(0F, 0F, 0F, 14, 1, 2);
        Shape4.setRotationPoint(-6F, -24F, -7F);
        Shape4.setTextureSize(256, 256);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, -1.570796F, 0F);
        Shape5 = new ModelRenderer(this, 15, 6);
        Shape5.addBox(0F, 0F, 0F, 14, 15, 1);
        Shape5.setRotationPoint(-6.5F, 6F, -7F);
        Shape5.setTextureSize(256, 256);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, -1.570796F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 14, 2, 2);
        Shape6.setRotationPoint(-6F, 4F, -7F);
        Shape6.setTextureSize(256, 256);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, -1.570796F, 0F);
        Shape7 = new ModelRenderer(this, 16, 24);
        Shape7.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape7.setRotationPoint(-7.5F, -23F, -5.5F);
        Shape7.setTextureSize(256, 256);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 16, 24);
        Shape8.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape8.setRotationPoint(-7.5F, -23F, -3.5F);
        Shape8.setTextureSize(256, 256);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 16, 24);
        Shape9.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape9.setRotationPoint(-7.5F, -23F, -1.5F);
        Shape9.setTextureSize(256, 256);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 16, 24);
        Shape10.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape10.setRotationPoint(-7.5F, -23F, 4.5F);
        Shape10.setTextureSize(256, 256);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 16, 24);
        Shape11.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape11.setRotationPoint(-7.5F, -23F, 2.5F);
        Shape11.setTextureSize(256, 256);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 16, 24);
        Shape12.addBox(0F, 0F, 0F, 1, 27, 1);
        Shape12.setRotationPoint(-7.5F, -23F, 0.5F);
        Shape12.setTextureSize(256, 256);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 23, 24);
        Shape14.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape14.setRotationPoint(-6.5F, -21F, -7F);
        Shape14.setTextureSize(256, 256);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, -1.570796F, 0F);
        Shape15 = new ModelRenderer(this, 23, 24);
        Shape15.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape15.setRotationPoint(-6.5F, -1F, -7F);
        Shape15.setTextureSize(256, 256);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, -1.570796F, 0F);
        Shape16 = new ModelRenderer(this, 23, 24);
        Shape16.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape16.setRotationPoint(-6.5F, -19F, -7F);
        Shape16.setTextureSize(256, 256);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, -1.570796F, 0F);
        Shape17 = new ModelRenderer(this, 23, 24);
        Shape17.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape17.setRotationPoint(-6.5F, -11.5F, -7F);
        Shape17.setTextureSize(256, 256);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, -1.570796F, 0F);
        Shape18 = new ModelRenderer(this, 23, 24);
        Shape18.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape18.setRotationPoint(-6.5F, -9.5F, -7F);
        Shape18.setTextureSize(256, 256);
        Shape18.mirror = true;
        setRotation(Shape18, 0F, -1.570796F, 0F);
        Shape19 = new ModelRenderer(this, 23, 30);
        Shape19.addBox(0F, 0F, 0F, 14, 27, 0);
        Shape19.setRotationPoint(-7F, -23F, -7F);
        Shape19.setTextureSize(256, 256);
        Shape19.mirror = true;
        setRotation(Shape19, 0F, -1.570796F, 0F);
        Shape13 = new ModelRenderer(this, 23, 26);
        Shape13.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape13.setRotationPoint(-6.5F, 1F, -7F);
        Shape13.setTextureSize(256, 256);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, -1.570796F, 0F);
        Shape20 = new ModelRenderer(this, 46, 12);
        Shape20.addBox(0F, 0F, 0F, 16, 1, 2);
        Shape20.setRotationPoint(-8F, 23F, -8F);
        Shape20.setTextureSize(256, 256);
        Shape20.mirror = true;
        setRotation(Shape20, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape14.render(f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape18.render(f5);
        Shape19.render(f5);
        Shape13.render(f5);
        Shape20.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

