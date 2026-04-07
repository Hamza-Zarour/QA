package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecipeBook Tests")
public class TestRecipeBook {

    RecipeBook recipeBook;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
    }

    private Recipe createRecipe(String name, String price) throws RecipeException {// it throws RecipeException because setPrice can throw it.
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        return r;
    }

    // ── addRecipe ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Add a new recipe succeeds and is stored at index 0")
    void testAddRecipe() throws RecipeException {
        Recipe r = createRecipe("Latte", "5");
        assertTrue(recipeBook.addRecipe(r));
        assertEquals("Latte", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Add a new recipe into full recipeBook returns false")
    void testAddToFullRecipeBook() throws RecipeException {
        recipeBook.addRecipe(createRecipe("R1", "1"));
        recipeBook.addRecipe(createRecipe("R2", "2"));
        recipeBook.addRecipe(createRecipe("R3", "3"));
        recipeBook.addRecipe(createRecipe("R4", "4"));
        assertFalse(recipeBook.addRecipe(createRecipe("R5", "5")));// Adding a 5th recipe should return false
    }

    @Test
    @DisplayName("Adding a duplicate recipe returns false")
    void testAddDuplicateRecipe() throws RecipeException {
        Recipe r = createRecipe("Espresso", "3");
        recipeBook.addRecipe(r);
        assertFalse(recipeBook.addRecipe(r));// Adding the same recipe again should return false
    }

    @ParameterizedTest
    @DisplayName("Parameterized: four unique recipes can each be added")
    @ValueSource(strings = {"Coffee", "Tea", "Juice", "Milk"})
    void testAddUniqueRecipes(String name) throws RecipeException {// it throws RecipeException because createRecipe can throw it.
        Recipe r = createRecipe(name, "3");
        assertTrue(recipeBook.addRecipe(r));
    }

    // ── deleteRecipe ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Delete an existing recipe returns its name")
    void testDeleteExistingRecipe() throws RecipeException {
        recipeBook.addRecipe(createRecipe("Mocha", "4"));
        String deleted = recipeBook.deleteRecipe(0);
        assertEquals("Mocha", deleted);
    }

    @Test
    @DisplayName("Delete a null slot returns null")
    void testDeleteNonExistingRecipe() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    // ── editRecipe ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("Edit an existing recipe returns old name")
    void testEditExistingRecipe() throws RecipeException {
        recipeBook.addRecipe(createRecipe("Cappuccino", "4"));
        Recipe replacement = createRecipe("Updated", "6");
        String oldName = recipeBook.editRecipe(0, replacement);
        assertEquals("Cappuccino", oldName);
    }

    @Test
    @DisplayName("Edit a null slot returns null")
    void testEditNonExistingRecipe() throws RecipeException {
        assertNull(recipeBook.editRecipe(0, createRecipe("Ghost", "1")));
    }

    // ── getRecipes ───────────────────────────────────────────────────────────

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("getRecipes returns an array of length 4 within timeout")
    void testGetRecipesTimeout() {
        assertNotNull(recipeBook.getRecipes());
        assertEquals(4, recipeBook.getRecipes().length);
    }
}