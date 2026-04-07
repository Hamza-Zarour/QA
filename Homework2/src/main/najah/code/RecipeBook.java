package main.najah.code;

public class RecipeBook {
	
	/** Array of recipes in coffee maker*/
	private Recipe [] recipeArray;
	/** Number of recipes in coffee maker */
	private final int NUM_RECIPES = 4; // Coffee maker can only hold 4 recipes
	
	/**
	 * Default constructor for a RecipeBook.
	 */
	public RecipeBook() {
		recipeArray = new Recipe[NUM_RECIPES];
	}
	
	/**
	 * Returns the recipe array.
	 * @param r
	 * @return Recipe[]
	 */
    // synchronized to prevent concurrent modification issues on the recipe array
	public synchronized Recipe[] getRecipes() {
		return recipeArray;
	}
	// addRecipe checks if the recipe already exists in the array and
    // if there is an empty spot to add the recipe.
	public synchronized boolean addRecipe(Recipe r) {

		boolean exists = false;
		//Check that recipe doesn't already exist in array
		for (int i = 0; i < recipeArray.length; i++ ) {
			if (r.equals(recipeArray[i])) {
				exists = true;
			}
		}

		boolean added = false;
		//Check for first empty spot in array if r exist.
		if (!exists) {
			for (int i = 0; i < recipeArray.length && !added; i++) {
				if (recipeArray[i] == null) {
					recipeArray[i] = r;
					added = true;
				}
			}
		}
		return added;
	}

	/**
	 * Returns the name of the recipe deleted at the position specified
	 * and null if the recipe does not exist.
	 * @param recipeToDelete
	 * @return String
	 */
    // deleteRecipe is for deleting a recipe at a specific index and returning
    // the name of the deleted recipe, and null if there is no recipe at that index.
	public synchronized String deleteRecipe(int recipeToDelete) {
		if (recipeArray[recipeToDelete] != null) {
			String recipeName = recipeArray[recipeToDelete].getName();
			recipeArray[recipeToDelete] = new Recipe();
            // Set the recipe at the specified index to a new empty recipe
            // (bad design, but it is what the original code does).
			return recipeName;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the name of the recipe edited at the position specified
	 * and null if the recipe does not exist.
	 * @param recipeToEdit
	 * @param newRecipe
	 * @return String
	 */
    // editRecipe is for editing a recipe at a specific index.
    // It replaces the existing recipe with the new recipe and returns the name of the old recipe.
    // If there is no recipe at the specified index, it returns null.
	public synchronized String editRecipe(int recipeToEdit, Recipe newRecipe) {
		if (recipeArray[recipeToEdit] != null) {
			String recipeName = recipeArray[recipeToEdit].getName();
			newRecipe.setName("");// bad design, but it is what the original code does.
			recipeArray[recipeToEdit] = newRecipe;
			return recipeName;
		} else {
			return null;
		}
	}

}
