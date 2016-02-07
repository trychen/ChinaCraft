package unstudio.chinacraft.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.client.model.ModelPotteryBase;
import unstudio.chinacraft.util.PotteryManager;

public class ItemPotteryBlockRenderer implements IItemRenderer {

    private ModelPotteryBase model;
    private TileEntity tile;
    private double x, y, z;

    public ItemPotteryBlockRenderer(TileEntity tileentity, double xloc, double yloc, double zloc) {
        if (tileentity == null) {
            return;
        }
        tile = tileentity;
        x = xloc;
        y = yloc;
        z = zloc;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (item.hasTagCompound())
            model = PotteryManager.Instance().getBlockPottery(item.getTagCompound().getString("PotteryType"))
                    .getModel();
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation("chinacraft:textures/models/block/empty.png"));
        model.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}
