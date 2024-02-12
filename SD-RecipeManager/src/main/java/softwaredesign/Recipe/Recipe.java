package softwaredesign.Recipe;

import softwaredesign.Ingredients.IngredientList;
import softwaredesign.Ingredients.IngredientList;
import softwaredesign.Instructions.InstructionList;


public class Recipe {
    private String name;
    private final IngredientList ingredientList;
    private final InstructionList instructionList;
    private String description;
    private int Servings;
    private int CookingTime;
    private String recipeTag;
    private String category;
    public Recipe(String Name, IngredientList recipeIngredientList, InstructionList recipeInstructionList, String recipeDescription, int servingSize, int cookingTime, String Category, String tag){
        this.name = Name;
        this.ingredientList = recipeIngredientList;
        this.instructionList = recipeInstructionList;
        this.description = recipeDescription;
        this.Servings = servingSize;
        this.CookingTime = cookingTime;
        this.category = Category;
        this.recipeTag = tag;

    }
    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getDescription(){
     return this.description;
    }

    public void setDescription(String newDescription){
        description = newDescription;
    }

    public IngredientList getIngredients(){
        return this.ingredientList;
    }

    public void setServings(int newServings) {
        Servings = newServings;
    }

    public int getCookingTime(){
        return CookingTime;
    }

    public void setCookingTime(int newTime){
        CookingTime = newTime;

    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String newCategory) {
        category = newCategory;
    }
    public InstructionList getInstructionList(){
        return this.instructionList;
    }

}