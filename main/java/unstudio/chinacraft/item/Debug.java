package unstudio.chinacraft.item;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TileSericultureFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class Debug extends Item{

	public Debug() {
		setCreativeTab(ChinaCraft.tabCore);
		setUnlocalizedName("debug");
		setMaxStackSize(1);
	}
	
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	if(world.isRemote) return false;
    	player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.firstline")));
		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.blockinfo")+": "+ (StatCollector.canTranslate(world.getBlock(x, y, z).getUnlocalizedName()+".name")? StatCollector.translateToLocal(world.getBlock(x, y, z).getUnlocalizedName()+".name") : StatCollector.translateToLocal(world.getBlock(x, y, z).getUnlocalizedName()+".default.name") )+ " "+ Block.getIdFromBlock(world.getBlock(x, y, z))+" "+ world.getBlock(x, y, z).getUnlocalizedName().replace("tile.","")));
		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.position")+": "+x+"/"+y+"/"+z+" (X/Y/Z)"));
    	player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.metadata")+": "+world.getBlockMetadata(x, y, z)));
    	TileEntity tile = world.getTileEntity(x, y, z);
    	if(world.getTileEntity(x, y, z)!=null){
    	player.addChatMessage(new ChatComponentText("TileEntity:"+tile.getClass().getSimpleName()));
    	}
    	if(tile instanceof TileBuhrimill) {
    		
    	}else if(tile instanceof TileSericultureFrame) {
    		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.deathrate")+": "+((TileSericultureFrame)tile).mortality));
    		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.progress")+": "+((TileSericultureFrame)tile).schedule));
    	}
		return true;
    }

	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(StatCollector.translateToLocal("item.debug.lore"));
	}
}
