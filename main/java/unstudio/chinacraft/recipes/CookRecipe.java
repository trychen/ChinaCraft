package unstudio.chinacraft.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import unstudio.chinacraft.util.BlockPottery;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CookRecipe {

	private ItemStack[] inputs;
	private ItemStack[] outputs;
	private int cooktime;
	
	public CookRecipe(ItemStack[] inputs,ItemStack[] outputs,int cooktime) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.cooktime = cooktime;
	}
	
	public CookRecipe(Object[] inputs,Object[] outputs,int cooktime) {
		 ItemStack[] is = new ItemStack[inputs.length];
		 ItemStack[] os = new ItemStack[outputs.length];
		for(int i = 0;i<inputs.length;i++) {
			if(inputs[i] instanceof ItemStack) {
				is[i] = (ItemStack) inputs[i];
			}else if(inputs[i] instanceof Item) {
				is[i] = new ItemStack((Item)inputs[i]);
			}else if(inputs[i] instanceof Block) {
				is[i] = new ItemStack((Block)inputs[i]);
			}else {
				
			}
		}
		for(int i = 0;i<outputs.length;i++) {
			if(outputs[i] instanceof ItemStack) {
				os[i] = (ItemStack) outputs[i];
			}else if(inputs[i] instanceof Item) {
				os[i] = new ItemStack((Item)outputs[i]);
			}else if(inputs[i] instanceof Block) {
				os[i] = new ItemStack((Block)outputs[i]);
			}else {
				
			}
		}
		this.inputs = is;
		this.outputs = os;
		this.cooktime = cooktime;
	}

	public ItemStack[] getInputs() {
		return inputs;
	}

	public ItemStack[] getOutputs() {
		return outputs;
	}

	public int getCooktime() {
		return cooktime;
	}
	
	private static ArrayList<CookRecipe> recipes = new ArrayList<CookRecipe>();
	
	public CookRecipe registerCookRecipe(Object[] inputs,Object[] outputs,int cooktime) {
		CookRecipe recipe = new CookRecipe(inputs, outputs, cooktime);
		recipes.add(recipe);
		return recipe;
	}
	
	public CookRecipe registerCookRecipe(ItemStack[] inputs,ItemStack[] outputs,int cooktime) {
		CookRecipe recipe = new CookRecipe(inputs, outputs, cooktime);
		recipes.add(recipe);
		return recipe;
	}
	
	public CookRecipe getCookRecipe(ItemStack[] inputs) {
		if(inputs == null||inputs.length == 0) return null;
		for (CookRecipe recipe : recipes) {
			if(inputs.length!=recipe.getInputs().length)continue;
			else {
				int i=0;
				for(ItemStack item1:recipe.getInputs()) {
					for(ItemStack item2:inputs) {
						if(item1.isItemEqual(item2)) {
							i++;
							break;
						}
					}
				}
				if(i==recipe.inputs.length)return recipe;
			}
		}
		return null;
	}
}
