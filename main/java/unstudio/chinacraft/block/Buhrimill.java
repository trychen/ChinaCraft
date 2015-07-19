package unstudio.chinacraft.block;

import java.util.List;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Buhrimill extends BlockContainer{
	
	public Buhrimill() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("buhrimill"));
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
		setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
	}
	
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBoundsForItemRender();
    }
    
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

	
	public TileEntity createNewTileEntity(World var1, int var2){
		return new TileEntityCustom();
		}
	
	public static class TileEntityCustom extends TileEntity {}
	public static class TileEntityCustomRenderer extends TileEntitySpecialRenderer {
	private final ModelBuhrimill model;
	public TileEntityCustomRenderer() {this.model = new ModelBuhrimill();}
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) { 
	GL11.glPushMatrix();
	GL11.glTranslatef((float)x+0.5F, (float)y+1.0F, (float)z+0.5F);
	ResourceLocation textures = (new ResourceLocation("Buhrimill.png"));
	Minecraft.getMinecraft().renderEngine.bindTexture(textures);
	GL11.glPushMatrix();
	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
	GL11.glPushMatrix();
	GL11.glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
	this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	GL11.glPopMatrix();
	GL11.glPopMatrix();
	GL11.glPopMatrix();}
	}

	public static class ModelBuhrimill extends ModelBase
	{
	  //fields
	    ModelRenderer Bottom;
	    ModelRenderer i1;
	    ModelRenderer i2;
	    ModelRenderer i3;
	    ModelRenderer i4;
	    ModelRenderer i5;
	    ModelRenderer i6;
	  
	  public ModelBuhrimill()
	  {
	    textureWidth = 128;
	    textureHeight = 128;
	    
	      Bottom = new ModelRenderer(this, 0, 0);
	      Bottom.addBox(-8F, 0F, -8F, 16, 7, 16);
	      Bottom.setRotationPoint(0F, 9F, 0F);
	      Bottom.setTextureSize(64, 32);
	      Bottom.mirror = true;
	      setRotation(Bottom, 0F, 0F, 0F);
	      i1 = new ModelRenderer(this, 0, 32);
	      i1.addBox(-8F, -1F, -8F, 16, 1, 1);
	      i1.setRotationPoint(0F, 9F, 0F);
	      i1.setTextureSize(64, 32);
	      i1.mirror = true;
	      setRotation(i1, 0F, 0F, 0F);
	      i2 = new ModelRenderer(this, 0, 32);
	      i2.addBox(-8F, -1F, -8F, 1, 1, 14);
	      i2.setRotationPoint(0F, 9F, 1F);
	      i2.setTextureSize(64, 32);
	      i2.mirror = true;
	      setRotation(i2, 0F, 0F, 0F);
	      i3 = new ModelRenderer(this, 0, 32);
	      i3.addBox(7F, -1F, -7F, 1, 1, 14);
	      i3.setRotationPoint(0F, 9F, 0F);
	      i3.setTextureSize(64, 32);
	      i3.mirror = true;
	      setRotation(i3, 0F, 0F, 0F);
	      i4 = new ModelRenderer(this, 0, 32);
	      i4.addBox(-8F, -1F, 7F, 16, 1, 1);
	      i4.setRotationPoint(0F, 9F, 0F);
	      i4.setTextureSize(64, 32);
	      i4.mirror = true;
	      setRotation(i4, 0F, 0F, 0F);
	      i5 = new ModelRenderer(this, 64, 0);
	      i5.addBox(-5F, -6F, -5F, 10, 6, 10);
	      i5.setRotationPoint(0F, 9F, 0F);
	      i5.setTextureSize(64, 32);
	      i5.mirror = true;
	      setRotation(i5, 0F, 0F, 0F);
	      i6 = new ModelRenderer(this, 64, 32);
	      i6.addBox(5F, -4F, 0F, 5, 1, 1);
	      i6.setRotationPoint(0F, 9F, 0F);
	      i6.setTextureSize(64, 32);
	      i6.mirror = true;
	      setRotation(i6, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
	    Bottom.render(f5);
	    i1.render(f5);
	    i2.render(f5);
	    i3.render(f5);
	    i4.render(f5);
	    i5.render(f5);
	    i6.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity e)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	  }

	}
}
