package unstudio.chinacraft.tdiant.test;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TdiantDebug_1 extends Block{

	public TdiantDebug_1() {
		super(Material.rock );
	}
	//tdiant���--��ʼ
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_1_.isRemote)
        {
            return true;
        }
        else
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	mc.displayGuiScreen(new unstudio.chinacraft.gui.GuiBuhrimill(mc.currentScreen));
            return true;
        }
    }
	//tdiant���--��
}
