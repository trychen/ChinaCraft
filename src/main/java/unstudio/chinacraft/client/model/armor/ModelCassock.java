package unstudio.chinacraft.client.model.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCassock extends ModelBiped {
    //fields
    ModelRenderer armorRightLeg;
    ModelRenderer armorLeftLeg;
    ModelRenderer armorInterBody;
    ModelRenderer armorRightArm;
    ModelRenderer armorLeftArm;
    ModelRenderer armorOuterBody;
    ModelRenderer armorOuterLeftArm;

    public ModelCassock() {
        super(0, 0, 256, 128);
        textureWidth = 256;
        textureHeight = 128;

        armorRightLeg = new ModelRenderer(this, 0, 16);
        armorRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        armorRightLeg.setRotationPoint(-2F, 12F, 0F);
        armorRightLeg.setTextureSize(256, 128);
        armorRightLeg.mirror = true;

//        armorLeftLeg.mirror = true;
        armorLeftLeg = new ModelRenderer(this, 0, 16);
        armorLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        armorLeftLeg.setRotationPoint(2F, 12F, 0F);
        armorLeftLeg.setTextureSize(256, 128);
        armorLeftLeg.mirror = true;

//        armorLeftLeg.mirror = false;
        armorInterBody = new ModelRenderer(this, 16, 16);
        armorInterBody.addBox(-4F, 0F, -2F, 8, 12, 4);
        armorInterBody.setRotationPoint(0F, 0F, 0F);
        armorInterBody.setTextureSize(256, 128);
        armorInterBody.mirror = true;

        armorRightArm = new ModelRenderer(this, 40, 16);
        armorRightArm.addBox(-4F, 0F, -2F, 4, 11, 4);
        armorRightArm.setRotationPoint(-4F, 0F, 0F);
        armorRightArm.setTextureSize(256, 128);
        armorRightArm.mirror = true;

        armorLeftArm = new ModelRenderer(this, 40, 16);
        armorLeftArm.addBox(0F, 0F, -2F, 4, 11, 4);
        armorLeftArm.setRotationPoint(4F, 0F, 0F);
        armorLeftArm.setTextureSize(256, 128);
        armorLeftArm.mirror = true;

        armorOuterBody = new ModelRenderer(this, 0, 32);
        armorOuterBody.addBox(-4.5F, -0.5F, -2.5F, 9, 14, 5);
        armorOuterBody.setRotationPoint(0F, 0F, 0F);
        armorOuterBody.setTextureSize(256, 128);
        armorOuterBody.mirror = true;

        armorOuterLeftArm = new ModelRenderer(this, 36, 32);
        armorOuterLeftArm.addBox(-2.5F, -0.5F, -2.5F, 5, 11, 5);
        armorOuterLeftArm.setRotationPoint(6F, 0F, 0F);
        armorOuterLeftArm.setTextureSize(256, 128);
        armorOuterLeftArm.mirror = true;

        bipedLeftArm.addChild(armorLeftArm);
        bipedLeftArm.addChild(armorOuterLeftArm);

        bipedBody.addChild(armorInterBody);
        bipedBody.addChild(armorOuterBody);

        bipedLeftLeg.addChild(armorLeftLeg);

        bipedRightLeg.addChild(armorRightLeg);
        bipedRightArm.addChild(armorRightArm);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        armorRightLeg.render(f5);
        armorLeftLeg.render(f5);
        armorInterBody.render(f5);
        armorRightArm.render(f5);
        armorLeftArm.render(f5);
        armorOuterBody.render(f5);
        armorOuterLeftArm.render(f5);
    }
}