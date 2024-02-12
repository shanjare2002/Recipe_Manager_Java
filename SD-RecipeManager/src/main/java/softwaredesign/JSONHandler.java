package softwaredesign;

import com.google.gson.*;
import softwaredesign.Recipe.Recipe;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class JSONHandler {
    static String filePath = new File("").getAbsolutePath();
    static ArrayList<User> usersList = new ArrayList<User>();
    static Gson gson = new Gson();
    static File[] userFiles;
    static String userFolder = filePath + "/src/main/java/softwaredesign/userStorage/";
    static File userfolder = new File(userFolder);
    static FileWriter writer;
    static FileReader reader;

    public static String getStorageReader(Path filename) throws IOException {
        return Files.readString(filename);
    }
    public static void getUserList() throws IOException {
        userFiles = userfolder.listFiles();
        assert userFiles != null;
        for (File file : userFiles) {
            String result =  getStorageReader(Path.of(userFolder + file.getName() + "/userDetails.json"));
            User user = gson.fromJson(result, User.class);
            usersList.add(user);
        }
    }

    public static void writeToUserFolder(String filename, Object object) throws IOException {
        writer = new FileWriter(filename);
        gson.toJson(object,writer);
        writer.close();
    }
    public static void createUserDirectory(User user) throws IOException {
        String newDirectoryPath = userFolder + user.getUsername();
        File newUser = new File(newDirectoryPath);
        boolean created = newUser.mkdir();
        File userDetails = new File(newDirectoryPath + "/userDetails.json");
        userDetails.createNewFile();
        writeToUserFolder(newDirectoryPath + "/userDetails.json", user);
    }
    public static ArrayList<Recipe> getRecipes(User user) throws IOException {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        File userFolder  =  new File(userfolder +"/" + user.getUsername());
        File[] recipes = userFolder.listFiles();
        for(File recipe: recipes){
            if(!recipe.getName().equals("userDetails.json")){
                String result = getStorageReader(Path.of(recipe.getAbsolutePath()));
                recipeList.add(gson.fromJson(result,Recipe.class));
            }
        }
        return recipeList;
    }
    public static void recipeStorage(String username, Recipe newRecipe) throws IOException {
        String newRecipePath = userFolder + username + "/" + newRecipe.getName() + ".json";
        File newRecipeFile = new File(newRecipePath);
        newRecipeFile.createNewFile();
        writeToUserFolder(newRecipePath, newRecipe);
    }
    public static void removeRecipe(String username, Recipe existingRecipe){
        String userpath =  userFolder + username + "/" + existingRecipe.getName() + ".json";
        File file = new File(userpath);
        file.delete();
    }

}