package softwaredesign.Instructions;
import java.util.Scanner;
public class Instruction {

    private final int id;
    private String annotation;
    private String instruction;


    public void setInstruction(String inputInstruction){
        instruction = inputInstruction;
    }
    public String getAnnotation(){
        return this.annotation;
    }

    public void setAnnotation(){
        System.out.println("Set the annotation for this annotation: ");
        Scanner scanner = new Scanner(System.in);
        annotation = scanner.nextLine();
    }

    public Instruction(int iD, String Instruction, String Annotation){
        this.id = iD;
        this.instruction = Instruction;
        this.annotation = Annotation;
    }
    public String getInstruction(){
        return this.instruction;
    }
    public int getId(){
        return  this.id;
    }
}