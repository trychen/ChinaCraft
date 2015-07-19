package unstudio.chinacraft.block;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Buhrimill extends BlockContainer {

	IIcon top, down;

	public Buhrimill() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("buhrimill"));
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 1);
	}

	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
			int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
			List p_149743_6_, Entity p_149743_7_) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
				p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 0.875F, 0.75F);
		super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
				p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		this.setBlockBoundsForItemRender();
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityFurnace)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }


	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int par2) {
		if (i == 0)
			return down;
		else if (i >= 1 && i <= 6)
			return top;
		else
			return top;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon("chinacraft:empty");
		this.down = reg.registerIcon("minecraft:stone");
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return ChinaCraft.itemBuhrimill;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_,
			int p_149694_4_) {
		return ChinaCraft.itemBuhrimill;
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityCustom();
	}

	public static class TileEntityCustom extends TileEntity {
	}

	public static class TileEntityCustomRenderer extends
			TileEntitySpecialRenderer {
		private final ModelBuhrimill model;

		public TileEntityCustomRenderer() {
			this.model = new ModelBuhrimill();
		}

		@Override
		public void renderTileEntityAt(TileEntity te, double x, double y,
				double z, float scale) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.0F,
					(float) z + 0.5F);
			ResourceLocation textures = new ResourceLocation(
					"chinacraft:textures/models/block/buhrimill.png");
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
			this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
					0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	public static class ModelBuhrimill extends ModelBase {
		// fields
		ModelRenderer Bottom;
		ModelRenderer i1;
		ModelRenderer i2;
		ModelRenderer i3;
		ModelRenderer i4;
		ModelRenderer i5;
		ModelRenderer i6;

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

		private void setRotation(ModelRenderer model, float x, float y, float z) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}

		public void setRotationAngles(float f, float f1, float f2, float f3,
				float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		}

	}
}
