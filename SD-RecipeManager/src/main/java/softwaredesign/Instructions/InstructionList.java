package softwaredesign.Instructions;

import java.util.ArrayList;

import java.util.Scanner;
import softwaredesign.usersInterface;

public class InstructionList {
    public ArrayList<Instruction> instructions;
    public InstructionList(ArrayList<Instruction> recipeInstructions){

        instructions = recipeInstructions;
    }


    public void editInstruction()
    {
        Instruction instruction;
        System.out.println("What would you like to edit:");
        String[] options = {"Instruction","Add","Remove"};
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }
        int option = scanner.nextInt();

        switch(option){
            case 1:
                instruction = usersInterface.getInstruction(instructions);
                System.out.println("Enter modified instruction");
                String newInstruction  = scanner.nextLine();
                assert instruction != null;
                instruction.setInstruction(newInstruction);

            case 2:
                instructions.add(usersInterface.getInstrucitonInput(instructions.size()));
                break;
            case 3:
                instruction = usersInterface.getInstruction(instructions);
                removeInstruction(instructions.indexOf(instruction));
        }
    }
    public void removeInstruction(int instructionIndex){
        instructions.remove(instructionIndex - 1);

    }
}

