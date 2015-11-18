package unstudio.chinacraft.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.block.model.ModelPotteryBase;
import unstudio.chinacraft.tileentity.TilePotteryBase;
import unstudio.chinacraft.util.PotteryManager;

public class TileEntityPotteryBlockRenderer extends TileEntitySpecialRenderer {
	
	public ResourceLocation textures = new ResourceLocation("chinacraft:textures/models/block/empty.png") ;

	public TileEntityPotteryBlockRenderer() {
	}
	
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
    	ModelPotteryBase model = PotteryManager.Instance().getBlockPottery(((TilePotteryBase)tile).getPotteryType()).getModel();
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glPushMatrix();
		GL11.glRotatef(tile.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
    }
}
