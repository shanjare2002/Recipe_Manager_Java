package softwaredesign;

import softwaredesign.Ingredients.Ingredient;
import softwaredesign.Instructions.Instruction;
import softwaredesign.Recipe.Recipe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class usersInterface {
    static Scanner scanner = new Scanner(System.in);

    public static String getUsername(int i) {
        return getString(i, scanner);
    }

    static String getString(int flag, Scanner scanner) {

        if (flag == 0) {
            System.out.print("If you already have an account enter ur userName if you want to create one enter create: ");
            System.out.println("");
            System.out.print("Enter:");
        } else if (flag == 1) {
            System.out.print("Enter username again: ");
        } else {
            System.out.print("Enter Username:");
        }
        String username = scanner.nextLine();
        if (Objects.equals(username, "create")) {
            return null;
        }
        return username;
    }

    public static boolean exitProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you wanna exit? then enter 'exit' enter any other letter ");
        String exit = scanner.nextLine();
        return Objects.equals(exit, "exit");
    }

    public static int getPIN() {
        System.out.print("Select a PIN preferably a four digit number: ");
        return scanner.nextInt();
    }

    public static User handleExistingUsers(String username) throws IOException {
        User currentUser = userList.existingUser(username);
        while (currentUser == null) {
            System.out.print("Sorry Wrong Username Entered try again: ");
            if (exitProgram()) {
                return null;
            }
            currentUser = userList.existingUser(getUsername(1));
        }
        return currentUser;
    }

    public static String getRecipeNameInput(){
        System.out.print("Enter the name of this recipe: ");

        return scanner.nextLine();
    }

    public static Ingredient getIngredientInput() {
        System.out.print("Enter the name of the ingredient: ");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();
        System.out.print("Enter the required amount, the unit and quantity should be filled in respectively. ");
        System.out.print("Enter the unit: ");
        Scanner scanner3 = new Scanner(System.in);
        String unit = scanner3.nextLine();
        System.out.print("Enter the quantity: ");
        Scanner scanner4 = new Scanner(System.in);
        int quantity = scanner4.nextInt();
        return new Ingredient(name, quantity, unit);
    }

    public static ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        System.out.print("Would you like to add an ingredient? yes/no:  ");
        String command = scanner.nextLine();

        while (Objects.equals(command, "yes")) {
            ingredients.add(getIngredientInput());
            System.out.print("Are you done? yes/no:  ");
            Scanner scanner2 = new Scanner(System.in);
            String command2 = scanner2.next();
            if (Objects.equals(command2, "yes")){
                break;
            }
        }
        return ingredients;
    }

    public static Instruction getInstrucitonInput(int index) {
        System.out.print("Enter Instruction:");
        Scanner scanner3 = new Scanner(System.in);
        String instruction =  scanner3.nextLine();
        return new Instruction(index, instruction, "");
    }
    protected static ArrayList<Instruction> getInstructions() {
        ArrayList<Instruction> instructions = new ArrayList<>();
        System.out.print("Would you like to add an instruction? yes/no: \n");
        int index = 0;
        Scanner scanner3 = new Scanner(System.in);
        String command = scanner3.nextLine();
        while (Objects.equals(command, "yes")) {
            instructions.add(getInstrucitonInput(index));
            index += 1;
            System.out.print("Are you done? yes/no:  \n");
            Scanner scanner2 = new Scanner(System.in);
            String command2 = scanner2.nextLine();
            if (Objects.equals(command2, "yes")){
                break;
            }

        }
        return instructions;
    }

    protected static String getDescriptionInput(){
        System.out.print("Enter a short description of the recipe: ");
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextLine();
    }
    protected static int getCookingTimeInput(){
        System.out.print("Enter the cooking time of this recipe: ");
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextInt();
    }
    protected static int getServingSizeInput(){
        System.out.print("Enter the the amount of persons this recipe serves: ");
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextInt();
    }
    protected static String getCategoryInput(){
        System.out.print("Enter recipe category: ");
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextLine();
    }
   protected  static void startMenu(RecipeManager letscook) throws IOException {
        System.out.println("Welcome to the Recipe Manager! What would you like to do?");
        String inputString = getUserInput();
        switch(inputString){
            case "1":
                System.out.println("Creating a recipe...");
                letscook.createRecipe();
                break;
            case "2":
                System.out.println("Executing a recipe...");
                letscook.executeRecipe();

                break;
            case "3":
                System.out.println("Type the name of the recipe you would like to search (Press enter if you would like to skip this step):");
                Scanner scann = new Scanner(System.in);
                String name = scann.nextLine();
                System.out.println("Type the category of the recipe you would like to search (Press enter if you would  like to skip this step):");
                System.out.println("For example vegetarian, normal, vegan:");
                Scanner scann2 = new Scanner(System.in);
                String category = scann2.nextLine();
                System.out.println("Type the time of the recipe you would like to search (Press 0 if you would like to skip this step):");
                System.out.println("For example 5-10 or 2-3");
                Scanner scann3 = new Scanner(System.in);
                String timeInterval = scann3.nextLine();
                ArrayList<Recipe> finalSearch = new ArrayList<Recipe>();
                if(timeInterval.equals("0"))
                {
                    finalSearch = letscook.searchRecipe(category,name,0);
                    for(int i = 0; i < finalSearch.size(); i++) {
                        System.out.println(i+1+". "+finalSearch.get(i).getName());
                    }
                }
                else {
                    String time1 = "";
                    String time2 = "";
                    for (int i = 0; i < timeInterval.length(); i++) {
                        int lineIndex = 0;
                        if (timeInterval.charAt(i) == '-') {
                            lineIndex = i;
                            for (int j = 0; j < timeInterval.length(); j++) {
                                if (j < lineIndex) {
                                    time1 = time1 + timeInterval.charAt(j);
                                }
                                if (j > lineIndex) {
                                    time2 = time2 + timeInterval.charAt(j);
                                }
                            }
                        }
                    }

                    int lowValue = Integer.parseInt(time1);
                    int highValue = Integer.parseInt(time2);
                    ArrayList<Recipe> adder = new ArrayList<Recipe>();
                    for (int i = lowValue; i <= highValue; i++) {
                        adder = letscook.searchRecipe(category, name, i);
                        finalSearch.addAll(adder);

                    }
                    for (int i = 0; i < finalSearch.size(); i++) {
                        System.out.println(i + 1 + ". " + finalSearch.get(i).getName());
                    }
                    System.out.println(finalSearch.size());
                }

                break;
            case "4":
                System.out.println("Type the name of the recipe you would like to edit:");
                Scanner scan = new Scanner(System.in);
                String recipeEdit = scan.nextLine();
                letscook.editRecipe(recipeEdit);
                break;
            case "5":
                letscook.removeRecipe();
                break;

        }
        System.out.println("Would you like to continue using our app our do yu want to exit. Press 1 if you want to continue and 2 to exit.");
        Scanner scannn = new Scanner(System.in);
        int newDescription = scannn.nextInt();
        if(newDescription == 1)
            startMenu(letscook);

    }
    protected static String getUserInput(){
        // Create an array of options
        String[] options = {"Create recipe", "Execute recipe", "SearchRecipe", "Edit recipe", "Remove Recipe"};
        // Print the list of options to the terminal
        System.out.println("Please select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        return option;
    }
   public static Ingredient getIngredient(ArrayList<Ingredient> recipeIngredients){
        System.out.println("What ingredient would you like to edit:");
        String name = scanner.next();
        for(Ingredient recipeIngredient:recipeIngredients){
            if(Objects.equals(recipeIngredient.getName(), name)){
                return recipeIngredient;
            }
        }
        return null;
    }
    public static Instruction getInstruction(ArrayList<Instruction> recipeInstructions){
        for(int i = 0; i < recipeInstructions.size(); i++){
            System.out.println((i + 1) + " - " + recipeInstructions.get(i).getInstruction());
        }
        System.out.println("What Instruction would you like to edit:");
        int index = scanner.nextInt();
        for(Instruction recipeInstruction:recipeInstructions){
            if(recipeInstruction.getId() == index - 1){
                return recipeInstruction;
            }
        }
        return null;
    }
    protected static String getRecipeTagInput(){
        System.out.println("What Recipe Tag would you like to add");
        return scanner.next();
    }


}

