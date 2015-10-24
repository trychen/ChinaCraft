package unstudio.chinacraft.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChinaZombie extends ModelBase
{
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer Shape0;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer ss1;
    ModelRenderer bb;
    ModelRenderer Shape1;
    ModelRenderer ss;

    public ModelChinaZombie()
    {
        textureWidth = 64;
        textureHeight = 128;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(64, 128);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 128);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 14, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 128);
        rightarm.mirror = true;
        setRotation(rightarm, -1.605496F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 14, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(64, 128);
        leftarm.mirror = true;
        setRotation(leftarm, -1.59868F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 128);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(64, 128);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        Shape0 = new ModelRenderer(this, 0, 62);
        Shape0.addBox(-3.5F, 0F, -3.5F, 7, 3, 7);
        Shape0.setRotationPoint(0F, -11F, 0F);
        Shape0.setTextureSize(64, 128);
        Shape0.mirror = true;
        setRotation(Shape0, 0F, 0.7853982F, 0F);
        Shape1 = new ModelRenderer(this, 0, 72);
        Shape1.addBox(-2F, 0F, -2F, 4, 3, 4);
        Shape1.setRotationPoint(0F, -12F, 0F);
        Shape1.setTextureSize(64, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0.7853982F, 0F);
        Shape2 = new ModelRenderer(this, 0, 79);
        Shape2.addBox(-0.5F, -2F, -0.5F, 1, 4, 1);
        Shape2.setRotationPoint(0F, -14F, 0F);
        Shape2.setTextureSize(64, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 84);
        Shape3.addBox(-1F, 0F, -1F, 2, 2, 2);
        Shape3.setRotationPoint(0F, -15F, 0F);
        Shape3.setTextureSize(64, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 62);
        Shape4.addBox(-3.5F, 0F, -3.5F, 7, 3, 7);
        Shape4.setRotationPoint(0F, -10F, 0F);
        Shape4.setTextureSize(64, 128);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 33);
        Shape5.addBox(1F, 0.1F, 0F, 3, 11, 1);
        Shape5.setRotationPoint(-2.5F, -7F, -5F);
        Shape5.setTextureSize(64, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 45);
        Shape6.addBox(-8F, 0F, -3F, 16, 2, 6);
        Shape6.setRotationPoint(0F, -9F, 0F);
        Shape6.setTextureSize(64, 128);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0.7853982F, 0F);
        Shape7 = new ModelRenderer(this, 0, 53);
        Shape7.addBox(-8F, 0F, -3.5F, 16, 2, 7);
        Shape7.setRotationPoint(0F, -9F, 0F);
        Shape7.setTextureSize(64, 128);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 45);
        Shape8.addBox(-8F, 0F, -3F, 16, 2, 6);
        Shape8.setRotationPoint(0F, -9F, 0F);
        Shape8.setTextureSize(64, 128);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, -0.7853982F, 0F);
        Shape9 = new ModelRenderer(this, 0, 53);
        Shape9.addBox(-8F, 0F, -3.5F, 16, 2, 7);
        Shape9.setRotationPoint(0F, -9F, 0F);
        Shape9.setTextureSize(64, 128);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, -1.579523F, 0F);
        ss1 = new ModelRenderer(this, 0, 88);
        ss1.addBox(-0.5F, 0F, -0.5F, 1, 14, 1);
        ss1.setRotationPoint(0F, -15F, 0F);
        ss1.setTextureSize(64, 128);
        ss1.mirror = true;
        setRotation(ss1, 0.9294653F, 0F, 0F);
        bb = new ModelRenderer(this, 16, 72);
        bb.addBox(-4.5F, 0F, -2.4F, 9, 10, 5);
        bb.setRotationPoint(0F, 12F, 0F);
        bb.setTextureSize(64, 128);
        bb.mirror = true;
        setRotation(bb, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 0, 103);
        Shape10.addBox(-1F, 0F, -1F, 2, 4, 1);
        Shape10.setRotationPoint(0F, -8F, 10F);
        Shape10.setTextureSize(64, 128);
        Shape10.mirror = true;
        setRotation(Shape10, 1.003822F, 0F, 0F);
        ss = new ModelRenderer(this, 4, 88);
        ss.addBox(-1F, 0F, 0F, 2, 14, 1);
        ss.setRotationPoint(-1F, 0F, 3F);
        ss.setTextureSize(64, 128);
        ss.mirror = true;
        setRotation(ss, 0F, 0F, 0F);
    }

    @Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        Shape0.render(f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        ss1.render(f5);
        bb.render(f5);
        Shape10.render(f5);
        ss.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5,e);
    }

}
