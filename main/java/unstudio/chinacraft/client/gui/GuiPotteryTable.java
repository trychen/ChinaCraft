package unstudio.chinacraft.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import unstudio.chinacraft.inventory.ContainerPotteryTable;

public class GuiPotteryTable extends GuiContainer{

	public GuiPotteryTable(EntityPlayer player) {
		super(new ContainerPotteryTable(player));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		String s = StatCollector.translateToLocal("gui.potterytable.title"); // 设置Gui标题
		this.fontRendererObj.drawString(s, this.xSize / 2
				- this.fontRendererObj.getStringWidth(s) / 2, 3, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/potterytable.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        this.buttonList.add(new GuiButton(0, k+59, l+22, 10, 20, "<"));
        this.buttonList.add(new GuiButton(0, k+106, l+22, 10, 20, ">"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		MinecraftServer server = FMLCommonHandler.instance()
				.getMinecraftServerInstance();
		World world = server.worldServers[0];
		if (button.id == 0) {
		}
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
	}
}
