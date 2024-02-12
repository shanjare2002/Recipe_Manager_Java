package softwaredesign;


import java.io.IOException;
import java.util.ArrayList;



public class Main {


    public static void main (String[] args) throws IOException {
        JSONHandler.getUserList();
        User currentUser = handleUserLogin();
        assert currentUser != null;
        RecipeManager letscook = RecipeManager.getInstance(currentUser, JSONHandler.getRecipes(currentUser));
        System.out.println("Welcome" + currentUser.getUsername());
        usersInterface.startMenu(letscook);
    }


    private static User handleUserLogin() throws IOException {
        User currentUser;
        String username = usersInterface.getUsername(0);
        if(username != null) {
            currentUser = usersInterface.handleExistingUsers(username);
            if(currentUser == null){
                return null;
            }
        }
        else{
            currentUser = userList.createUser();
        }
        while(!currentUser.login()){
            System.out.println("Enter PIN again");
        }
        return currentUser;
    }


}



