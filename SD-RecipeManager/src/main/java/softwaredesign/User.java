package softwaredesign;

import java.util.Scanner;

public class  User {
    private String username;
    private int userID;
    private int PIN;
    public User(String consUsername, int consPIN, int consUserID){
        username = consUsername;
        PIN = consPIN;
        userID = consUserID;
    }
    public boolean login(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a PIN: ");
        int inputPIN = scanner.nextInt();
        return inputPIN == this.PIN;
    }
    public int getUserId(){
        return this.userID;
    }
    public String getUsername(){
        return this.username;
    }
}
