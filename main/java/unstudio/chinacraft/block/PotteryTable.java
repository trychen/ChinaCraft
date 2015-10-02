package unstudio.chinacraft.block;

import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class PotteryTable extends Block{

	public PotteryTable() {
		super(Material.wood);
		setBlockName("potterytable");
		setHardness(1.0F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        	if(world.isRemote) return true;
        	p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_PotteryTable, world, x, y, z);
            return true;
    }

}
