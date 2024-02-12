package softwaredesign.Ingredients;
public class Ingredient {
    private String name;
    private String unit;
    private int quantity;

    public Ingredient(String ingredientName,int ingridientQuantity ,String ingredientUnit){
        this.name = ingredientName;
        this.unit = ingredientUnit;
        this.quantity = ingridientQuantity;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String newUnit) {
        unit = newUnit;
    }
}