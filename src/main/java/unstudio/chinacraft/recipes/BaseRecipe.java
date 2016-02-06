package unstudio.chinacraft.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BaseRecipe {

	private ItemStack[] inputs;
	private ItemStack[] outputs;
	
	public BaseRecipe(ItemStack[] inputs,ItemStack[] outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	public BaseRecipe(Object[] inputs,Object[] outputs) {
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
	}

	public ItemStack[] getInputs() {
		return inputs;
	}

	public ItemStack[] getOutputs() {
		return outputs;
	}
	
	public abstract boolean recipeEquals(BaseRecipe recipe);
	
	public abstract boolean inputsEquals(ItemStack[] inputs);
}
