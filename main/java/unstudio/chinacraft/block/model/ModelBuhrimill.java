package unstudio.chinacraft.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBuhrimill extends ModelBase {
	// fields
	public ModelRenderer Bottom;
	public ModelRenderer i1;
	public ModelRenderer i2;
	public ModelRenderer i3;
	public ModelRenderer i4;
	public ModelRenderer i5;
	public ModelRenderer i6;

	public ModelBuhrimill() {
		textureWidth = 128;
		textureHeight = 128;

		Bottom = new ModelRenderer(this, 0, 0);
		Bottom.addBox(-8F, 0F, -8F, 16, 7, 16);
		Bottom.setRotationPoint(0F, 9F, 0F);
		Bottom.setTextureSize(64, 32);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		i1 = new ModelRenderer(this, 0, 32);
		i1.addBox(-8F, -2F, -8F, 16, 2, 1);
		i1.setRotationPoint(0F, 9F, 0F);
		i1.setTextureSize(64, 32);
		i1.mirror = true;
		setRotation(i1, 0F, 0F, 0F);
		i2 = new ModelRenderer(this, 0, 32);
		i2.addBox(-8F, -2F, -8F, 1, 2, 14);
		i2.setRotationPoint(0F, 9F, 1F);
		i2.setTextureSize(64, 32);
		i2.mirror = true;
		setRotation(i2, 0F, 0F, 0F);
		i3 = new ModelRenderer(this, 0, 32);
		i3.addBox(7F, -2F, -7F, 1, 2, 14);
		i3.setRotationPoint(0F, 9F, 0F);
		i3.setTextureSize(64, 32);
		i3.mirror = true;
		setRotation(i3, 0F, 0F, 0F);
		i4 = new ModelRenderer(this, 0, 32);
		i4.addBox(-8F, -2F, 7F, 16, 2, 1);
		i4.setRotationPoint(0F, 9F, 0F);
		i4.setTextureSize(64, 32);
		i4.mirror = true;
		setRotation(i4, 0F, 0F, 0F);
		i5 = new ModelRenderer(this, 64, 0);
		i5.addBox(-5F, -7F, -5F, 10, 6, 10);
		i5.setRotationPoint(0F, 9F, 0F);
		i5.setTextureSize(64, 32);
		i5.mirror = true;
		setRotation(i5, 0F, 0F, 0F);
		i6 = new ModelRenderer(this, 64, 32);
		i6.addBox(4F, -5F, -1F, 6, 2, 2);
		i6.setRotationPoint(0F, 9F, 0F);
		i6.setTextureSize(64, 32);
		i6.mirror = true;
		setRotation(i6, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Bottom.render(f5);
		i1.render(f5);
		i2.render(f5);
		i3.render(f5);
		i4.render(f5);
		i5.render(f5);
		i6.render(f5);
	}

	public void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
