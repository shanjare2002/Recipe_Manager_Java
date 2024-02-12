package softwaredesign;
import java.io.IOException;
import java.util.ArrayList;

import softwaredesign.Ingredients.Ingredient;
import softwaredesign.Ingredients.IngredientList;
import softwaredesign.Instructions.Instruction;
import softwaredesign.Instructions.InstructionList;
import softwaredesign.Recipe.Recipe;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;



public class RecipeManager {
    private static RecipeManager instance = null;

    // Private constructor to prevent instantiation from outside
    private RecipeManager( User activeUser, ArrayList<Recipe> recipes) {

        this.currentUser = activeUser;
        this.recipesList = recipes;
    }

    // Public static method to retrieve the single instance of the RecipeManager
    public static RecipeManager getInstance( User activeUser, ArrayList<Recipe> recipes) {
        if (instance == null) {
            instance = new RecipeManager(activeUser, recipes);
        }
        return instance;
    }

    public  User currentUser;
    public  ArrayList<Recipe> recipesList;



    protected void createRecipe() throws IOException {
        Recipe  newRecipe = new Recipe( usersInterface.getRecipeNameInput(), new IngredientList(usersInterface.getIngredients()), new InstructionList(usersInterface.getInstructions()), usersInterface.getDescriptionInput(), usersInterface.getServingSizeInput(), usersInterface.getCookingTimeInput(), usersInterface.getCategoryInput(), usersInterface.getRecipeTagInput());
        JSONHandler.recipeStorage(currentUser.getUsername(), newRecipe);
        this.recipesList.add(newRecipe);
    }




    protected  ArrayList<Recipe> searchRecipe(String category, String name, int time){
        ArrayList<Recipe> categorySearch = new ArrayList<>();
        ArrayList<Recipe> nameSearch = new ArrayList<>();
        ArrayList<Recipe> timeSearch = new ArrayList<>();
        ArrayList<Recipe> finalSearch = new ArrayList<>();
        ArrayList<Recipe> finalSearch2 = new ArrayList<>();


        for(int i = 0; i < recipesList.size(); i++)
        {
            Recipe recipe = recipesList.get(i);
            if(!category.isEmpty()) {
                if (Objects.equals(recipe.getCategory(), category)) {
                    categorySearch.add(recipe);

                }

            }
            if(!name.isEmpty()) {
                if (Objects.equals(recipe.getName(), name)) {
                    nameSearch.add(recipe);
                }
            }

            if(time != 0) {
                if (recipe.getCookingTime() == time) {
                    timeSearch.add(recipe);
                }
            }

        }


        if(!category.isEmpty()) {
            for (Recipe value : categorySearch) {
                int check = 0;
                for (Recipe recipe : nameSearch) {
                    if (recipe == value)
                        check++;
                }

                for (Recipe search : timeSearch) {
                    if (search == value)
                        check++;
                }

                if (check == 2) {

                    int duplicate = 0;
                    for (Recipe search : finalSearch) {
                        if (search == value) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(value);
                }

                if (check == 1 && (timeSearch.size() == 0 && time == 0 || name.isEmpty())) {

                    int duplicate = 0;
                    for (Recipe search : finalSearch) {
                        if (search == value) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(value);
                }

                if (check == 0 && timeSearch.size() == 0 && nameSearch.size() == 0 && time == 0 && name.isEmpty()) {

                    int duplicate = 0;
                    for (Recipe search : finalSearch) {
                        if (search == value) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(value);
                }
            }
        }

        if(!name.isEmpty()) {
            for (Recipe search : nameSearch) {
                int check = 0;
                for (Recipe recipe : categorySearch) {
                    if (recipe == search)
                        check++;
                }

                for (Recipe recipe : timeSearch) {
                    if (recipe == search)
                        check++;
                }

                if (check == 2) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }

                if (check == 1 && (timeSearch.size() == 0 && time == 0 || category.isEmpty())) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }

                if (check == 0 && timeSearch.size() == 0 && categorySearch.size() == 0 && time == 0 && category.isEmpty()) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }
            }

        }
        if(time != 0) {
            for (Recipe search : timeSearch) {
                int check = 0;
                for (Recipe recipe : nameSearch) {
                    if (recipe == search)
                        check++;
                }

                for (Recipe recipe : categorySearch) {
                    if (recipe == search)
                        check++;
                }

                if (check == 2) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }

                if (check == 1 && (category.isEmpty() || name.isEmpty())) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }
                if (check == 0 && nameSearch.size() == 0 && categorySearch.size() == 0 && category.isEmpty() && name.isEmpty()) {

                    int duplicate = 0;
                    for (Recipe recipe : finalSearch) {
                        if (recipe == search) {
                            duplicate = 1;
                            break;
                        }
                    }

                    if (duplicate == 0)
                        finalSearch.add(search);
                }
            }
        }

        int[] check = new int[finalSearch.size()];

        for(int i = 0; i < finalSearch.size(); i++)
        {
            if(check[i] == 0)
            {
                finalSearch2.add(finalSearch.get(i));
                for(int j = 0; j < finalSearch.size(); j++)
                {
                    if(finalSearch.get(j) == finalSearch2.get(i))
                        check[j] = 1;
                }
            }
        }
        return finalSearch2;
    }
    protected void removeRecipe(){
        if(recipesList.size() == 0){
            System.out.println("Sorry there are no recipes in your list");
            return;
        }
        System.out.println("What recipe would you like to remove");
        for (int i = 0; i < recipesList.size(); i++) {
            System.out.println((i + 1) + " - " + recipesList.get(i).getName());
        }
            Scanner scanner = new Scanner(System.in) ;
            int option = scanner.nextInt();
            Recipe thisRecipe = recipesList.get(option-1);
            JSONHandler.removeRecipe(currentUser.getUsername(),thisRecipe);
            recipesList.remove(thisRecipe);
            recipesList.remove(thisRecipe);


    }

   protected void executeRecipe() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the recipe you would like to execute: ");

        if (recipesList.size() == 0){
            System.out.println("You don't have any recipes, would you like to create a new recipe? ");
            return;
        }

        for (int i = 0; i < recipesList.size(); i++) {
            System.out.println((i + 1) + " - " + recipesList.get(i).getName());
        }

        int option = scanner.nextInt();

        Recipe thisRecipe = recipesList.get(option-1);

        //creating all the variables needed from the json file
        String thisName = thisRecipe.getName();
        IngredientList thisRecipeList = thisRecipe.getIngredients();
        InstructionList thisInstructionList = thisRecipe.getInstructionList();
        String thiDescription = thisRecipe.getDescription();


        System.out.println("The following recipe will be executed: " + thisName + "\n");

        System.out.println(thiDescription + "\n");

        System.out.println("This recipe contains the following ingredients: \n");

        //Function to print the entire ingredient list
//        for (int i = 0; i < thisRecipeList.list.size(); i++) {
//            System.out.println((i+1) + " - " + thisRecipeList.list.get(i).quantity + " " +  thisRecipeList.list.get(i).unit + " " + thisRecipeList.list.get(i).name + "\n");
//        }
        for (Ingredient ingredient : thisRecipeList){
            System.out.println("- " + ingredient.getQuantity() + " " +  ingredient.getUnit() + " " + ingredient.getName() + "\n");

        }


        System.out.println("If you would like to proceed to the instructions, please enter 'yes'");

        Scanner scanner2 = new Scanner(System.in);
        String proceedOption = scanner2.nextLine();

        if (proceedOption.equals("yes")) {
            int currentIndex = 0;
            System.out.println((currentIndex+1) + " - " + thisInstructionList.instructions.get(currentIndex).getInstruction() + " (" + thisInstructionList.instructions.get(currentIndex).getAnnotation() + ")");

            String[] options = {"Next instruction", "Previous instruction", "Ingredients", "Add annotation", "Finish recipe"};

            while (currentIndex < thisInstructionList.instructions.size() && currentIndex >= 0){
                // Print the list of options to the terminal

                System.out.println("Please select an option:");
                for (int j = 0; j < options.length; j++) {
                    System.out.println((j + 1) + " - " + options[j]);
                }


                Scanner scanner3 = new Scanner(System.in);
                int option1 = scanner3.nextInt();

                switch(option1) {

                    case 1:
                        currentIndex += 1;
                        if(currentIndex > (thisInstructionList.instructions.size() - 1)){
                            break;
                        }
                        System.out.println((currentIndex + 1) + " - " + thisInstructionList.instructions.get(currentIndex).getInstruction() + " (" + thisInstructionList.instructions.get(currentIndex).getAnnotation() + ")");
                        if (currentIndex == (thisInstructionList.instructions.size()-1)){
                            System.out.println("This is the last step.");
                        }
                        break;


                    case 2:
                        currentIndex -= 1;
                        System.out.println((currentIndex + 1) + " - " + thisInstructionList.instructions.get(currentIndex).getInstruction() + " (" + thisInstructionList.instructions.get(currentIndex).getAnnotation() + ")");

                        break;

                    case 3:
                        System.out.print("Here are the ingredients you will need for this recipe: \n");

                        for (int i = 0; i < thisRecipeList.list.size(); i++) {
                            System.out.println((i + 1) + " - " + thisRecipeList.list.get(i).getQuantity() + " " + thisRecipeList.list.get(i).getUnit() + " " + thisRecipeList.list.get(i).getName() + "\n");
                        }
                        break;


                    case 4:

                        Instruction currentInstruction = thisInstructionList.instructions.get(currentIndex);
                        currentInstruction.setAnnotation();
                        JSONHandler.recipeStorage(currentUser.getUsername(), thisRecipe);
                        System.out.println((currentIndex + 1) + " - " + thisInstructionList.instructions.get(currentIndex).getInstruction()+ " (" + thisInstructionList.instructions.get(currentIndex).getAnnotation() + ")");
                        break;

                    case 5:
                        System.out.println("You have successfully completed the recipe!");
                        currentIndex += thisInstructionList.instructions.size();
                        break;
                }

            }

        }
    }
    protected void editRecipe(String RecipeName) throws IOException {
        int recipeIndex = -1;
        for (int i = 0; i < recipesList.size(); i++) {
            if (Objects.equals(recipesList.get(i).getName(), RecipeName))
                recipeIndex = i;

        }

        if (recipeIndex == -1)
            System.out.println("The recipe doesn't exist.");
        else {
            Scanner scan = new Scanner(System.in);
            Recipe recipe = recipesList.get(recipeIndex);
            System.out.println("What would you like to edit:");
            String[] options = {"Ingredient", "Instruction", "Recipe Description", "Time", "Servings", "Name", "Category"};
            // Print the list of options to the terminal
            System.out.println("Please select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + " - " + options[i]);
            }
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    recipe.getIngredients().editIngredient();
                    System.out.print("You have successfully edited your recipe.");
                    break;

                case 2:
                    recipe.getInstructionList().editInstruction();
                    System.out.print("You have successfully edited your recipe.");
                    break;
                case 3:
                    recipe.setDescription(usersInterface.getDescriptionInput());
                    break;
                case 4:
                    recipe.setCookingTime(usersInterface.getCookingTimeInput());
                    break;
                case 5:
                    recipe.setServings(usersInterface.getServingSizeInput());
                    break;
                case 6:
                    recipe.setName(usersInterface.getRecipeNameInput());
                    break;
                case 7:
                    recipe.setCategory(usersInterface.getCategoryInput());
                    break;

            }
            JSONHandler.recipeStorage(currentUser.getUsername(), recipe);
        }


    }

}


