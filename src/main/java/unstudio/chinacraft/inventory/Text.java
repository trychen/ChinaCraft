package unstudio.chinacraft.inventory;//based on master configuration

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class Text {

    public final static int GUIID = 2;

    public static class GuiContainerMod extends Container {

        public GuiContainerMod(EntityPlayer player) {}

        @Override
        public boolean canInteractWith(EntityPlayer player) {
            return true;
        }

        protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {}

        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            return null;
        }
    }

    public static class GuiWindow extends GuiContainer {

        private static final ResourceLocation texture = new ResourceLocation("ABC7194300611763407034.png");
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

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
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
        protected void mouseClicked(int par1, int par2, int par3) throws IOException {
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
            this.buttonList.add(new GuiButton(0, posX + (-50), posY + (-49), 20, 20, "<>"));

        }

        @Override
        protected void actionPerformed(GuiButton button) {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
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
