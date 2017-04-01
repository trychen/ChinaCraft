package unstudio.chinacraft.client.model.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelCassock extends ModelBiped {
    private final float scale = 1.005f;
    
    ModelRenderer armorRightLeg;
    ModelRenderer armorLeftLeg;
    ModelRenderer armorInterBody;
    ModelRenderer armorRightArm;
//    ModelRenderer armorLeftArm;
    ModelRenderer armorOuterBody;
    ModelRenderer armorOuterLeftArm;
    ModelRenderer armorOuterRightLeg;
    ModelRenderer armorOuterLeftLeg;

    public ModelCassock() {
        super(0.0f, 0, 256, 128);
        textureWidth = 256;
        textureHeight = 128;

        armorRightLeg = new ModelRendererScale(this, 0, 16, scale);
        armorRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        armorRightLeg.setTextureSize(256, 128);
        armorRightLeg.mirror = true;

        armorLeftLeg = new ModelRendererScale(this, 0, 16, scale);
        armorLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        armorLeftLeg.setTextureSize(256, 128);
        armorLeftLeg.mirror = true;

        armorInterBody = new ModelRendererScale(this, 16, 16, 1.009f);
        armorInterBody.addBox(-4F, 0F, -2F, 8, 12, 4);
        armorInterBody.setTextureSize(256, 128);
        armorInterBody.mirror = true;

        armorRightArm = new ModelRendererScale(this, 40, 16, scale);
        armorRightArm.addBox(-3F, -2.5F, -2F, 4, 11, 4);
        armorRightArm.setTextureSize(256, 128);
        armorRightArm.mirror = true;
//
//        armorLeftArm = new ModelRenderer(this, 40, 16);
//        armorLeftArm.addBox(0F, 0F, -2F, 4, 11, 4);
//        armorLeftArm.setTextureSize(256, 128);
//        armorLeftArm.mirror = true;

        armorOuterBody = new ModelRendererScale(this, 0, 32, 1.01f);
        armorOuterBody.addBox(-4.5F, -0.5F, -2.5F, 9, 14, 5);
        armorOuterBody.setTextureSize(256, 128);
        armorOuterBody.mirror = true;

        armorOuterLeftArm = new ModelRendererScale(this, 36, 32, 1.08f);
        armorOuterLeftArm.addBox(-2.5F, -0.5F, -2.5F, 5, 11, 5);
        armorOuterLeftArm.setRotationPoint(0.75F, -1.625F, 0F);
        armorOuterLeftArm.setTextureSize(256, 128);
        armorOuterLeftArm.mirror = true;

        armorOuterLeftLeg = new ModelRendererScale(this, 6, 45, scale);
        armorOuterLeftLeg.addBox(-2.5F, 0F, -2.5F, 5, 10, 5);
        armorOuterLeftLeg.setTextureSize(256, 128);
        armorOuterLeftLeg.mirror = true;

        armorOuterRightLeg = new ModelRendererScale(this, 0, 45, scale);
        armorOuterRightLeg.addBox(-2.5F, 0F, -2.5F, 5, 10, 5);
        armorOuterRightLeg.setTextureSize(256, 128);
        armorOuterRightLeg.mirror = true;

        //bipedLeftArm.addChild(armorLeftArm);
        bipedLeftArm.addChild(armorOuterLeftArm);
        bipedBody.addChild(armorInterBody);
        bipedBody.addChild(armorOuterBody);
        bipedLeftLeg.addChild(armorLeftLeg);
        bipedLeftLeg.addChild(armorOuterLeftLeg);
        bipedRightLeg.addChild(armorRightLeg);
        bipedRightLeg.addChild(armorOuterRightLeg);
        bipedRightArm.addChild(armorRightArm);
    }
}