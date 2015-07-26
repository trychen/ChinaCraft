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
		return this.tool;
	}
	public ItemStack getItem(){
		return this.item;
	}
	public ItemStack getOut(){
		return this.out;
	}
	
	private static ArrayList<JadeBenchRepair> recipes = new ArrayList<JadeBenchRepair>();
}
