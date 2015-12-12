package unstudio.chinacraft.recipes;

import java.util.ArrayList;
import java.util.List;

public class BaseRecipes<T extends BaseRecipe> {

	private List<T> Recipes;
	
	public BaseRecipes() {
		Recipes = new ArrayList<T>();
	}
}
