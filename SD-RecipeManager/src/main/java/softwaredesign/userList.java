package softwaredesign;

import java.io.IOException;
import java.util.Objects;

public class userList {
    public static int genrateRandomUserID(){
        int randUserID;
        boolean unique = true;
        while(true){
            randUserID = (int)(Math.random()*100);
            for(User user: JSONHandler.usersList){
                if (user.getUserId() == randUserID) {
                    unique = false;
                    break;
                }
            }
            if(unique){
                System.out.print(randUserID);
                return randUserID;
            }
        }
    }
    public static User createUser() throws IOException {
        String username = usersInterface.getUsername(2);
        while(existingUser(username) != null){
            System.out.println("existing username try again");
            username = usersInterface.getUsername(2);
        }
        User newUser = new User(username, usersInterface.getPIN(), genrateRandomUserID());
        JSONHandler.usersList.add(newUser);
        JSONHandler.createUserDirectory(newUser);
        return newUser ;
    }
    public static User existingUser(String username) throws IOException {
        new userList();
        for (User user :  JSONHandler.usersList) {
            if (Objects.equals(user.getUsername(), username)) {
                System.out.println("foundUser");
                return user;
            }
        }
        return null;
    }

}
