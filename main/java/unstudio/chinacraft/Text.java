package unstudio.chinacraft;//based on master condiguration

import cpw.mods.fml.common.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.server.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Text {

	public static int GUIID = 2;

	public static class GuiContainerMod extends Container {

		public GuiContainerMod(EntityPlayer player) {
		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {
			return true;
		}

		protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
			return null;
		}
	}

	public static class GuiWindow extends GuiContainer {

		int i = 0;
		int j = 0;
		int k = 0;
		EntityPlayer entity = null;

		public GuiWindow(World world, int i, int j, int k, EntityPlayer entity) {
			super(new GuiContainerMod(entity));
			this.i = i;
			this.j = j;
			this.k = k;
			this.entity = entity;
		}

		private static final ResourceLocation texture = new ResourceLocation(
				"ABC7194300611763407034.png");

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2,
				int par3) {
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			this.mc.renderEngine.bindTexture(texture);
			this.xSize = 127;
			this.ySize = 127;
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

			zLevel = 100.0F;

		}

		@Override
		protected void mouseClicked(int par1, int par2, int par3) {
			super.mouseClicked(par1, par2, par3);

		}

		@Override
		public void updateScreen() {
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;

		}

		@Override
		protected void keyTyped(char par1, int par2) {

			if (par2 != 28 && par2 != 156) {
				if (par2 == 1) {
					this.mc.displayGuiScreen((GuiScreen) null);
				}
			}

		}

		@Override
		protected void drawGuiContainerForegroundLayer(int par1, int par2) {
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;

		}

		@Override
		public void onGuiClosed() {
			Keyboard.enableRepeatEvents(false);
		}

		@Override
		public void initGui() {
			Keyboard.enableRepeatEvents(true);
			this.buttonList.clear();
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;
			this.buttonList.add(new GuiButton(0, posX + (-50), posY + (-49),
					20, 20, "<>"));

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
		public boolean doesGuiPauseGame() {
			return false;
		}

	}

}