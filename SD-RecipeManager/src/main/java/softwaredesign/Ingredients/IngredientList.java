package softwaredesign.Ingredients;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import softwaredesign.usersInterface;

public class IngredientList implements Iterable<Ingredient> {

    public ArrayList<Ingredient> list;

    public Iterator<Ingredient> iterator() {
        return this.list.iterator();
    }

    public void addIngredient(){
        Ingredient ingredient =  usersInterface.getIngredientInput();
        list.add(ingredient);
    }
    public IngredientList(ArrayList<Ingredient> ingredientList){
        this.list = ingredientList;
    }
    public void removeIngredient(int ingredientIndex){

        list.remove(ingredientIndex);
    }


    public void editIngredient() {
        Scanner scanner = new Scanner(System.in);
        Ingredient ingredient = null;
        System.out.println("What would you like to edit:");

        String[] options = {"Name", "Unit", "Quantity", "Remove", "Add"};

        System.out.println("Please select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }
        int option = scanner.nextInt();
        if(option != 5) {
            ingredient = usersInterface.getIngredient(list);
        }
        switch(option){
            case 1:
                System.out.println("what is the new name of the ingredient");
                String newName = scanner.next();
                assert ingredient != null;
                ingredient.setName(newName);
                break;
            case 2:
                System.out.println("what is the the unit of the ingredient");
                String newUnit = scanner.next();
                assert ingredient != null;
                ingredient.setUnit(newUnit);
                break;
            case 3:
                System.out.println("what is the the quantity of the ingredient");
                int newQuantity = scanner.nextInt();
                assert ingredient != null;
                ingredient.setQuantity(newQuantity);
                break;
            case 4:
                removeIngredient(list.indexOf(ingredient));
                break;
            case 5:
                addIngredient();
        }
    }
}