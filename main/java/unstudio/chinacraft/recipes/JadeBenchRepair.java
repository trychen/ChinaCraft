package unstudio.chinacraft.recipes;

import java.util.ArrayList;

import scala.annotation.meta.getter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class JadeBenchRepair{
	private final ItemStack tool;
	private final ItemStack item;
	private final ItemStack out;
	public JadeBenchRepair(ItemStack tool,ItemStack item,ItemStack out){
		this.tool = tool;
		this.item = item;
		this.out = out;
	}
	public ItemStack getTool(){
		return tool;
	}
	public ItemStack getItem(){
		return item;
	}
	public ItemStack getOut(){
		return out;
	}
	
	private static ArrayList<JadeBenchRepair> recipes = new ArrayList<JadeBenchRepair>();
	
	public static void registerJadeBenchRepair(ItemStack tool,ItemStack item,ItemStack out) {
		if(tool == null||out == null)return;
		recipes.add(new JadeBenchRepair(tool, item, out));
	}
	
	public static JadeBenchRepair getJadeBenchRepair(ItemStack tool,ItemStack item) {
		for (JadeBenchRepair r :recipes) {
			if(r.getTool().isItemEqual(tool)&&r.getItem().isItemEqual(item)||r == null) {
				return r;
			}
		}
		return null;
	}
	
	public static void clearJadeBenchReciper() {
		recipes.clear();
	}
}
