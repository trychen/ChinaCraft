package unstudio.chinacraft.api.recipes;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class BaseCookRecipes {
	private ArrayList<CookRecipe> recipes = new ArrayList<CookRecipe>();
	
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
				if(i==recipe.getInputs().length)return recipe;
			}
		}
		return null;
	}
}
