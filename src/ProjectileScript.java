public class ProjectileScript {
    public Instruction[] instructions;

    private int instructionIndex = 0;
    private int instructionCount = 0;


    public ProjectileScript(){
        instructions = new Instruction[Constants.SCRIPT_SIZE];
    }

    public void addInstruction(Instruction instruction){
        if(instructionCount < Constants.SCRIPT_SIZE){
            instructions[instructionCount] = instruction;
            instructionCount++;
        }
        else{
            System.out.println("Cannot add instruction, maximum script size reached!");
        }
    }

    public void executeInstruction(){
        if(instructionIndex == instructionCount){
            //System.out.println("End of script has been reached");
            return;
        }
        if(instructions[instructionIndex].execute()){
            instructionIndex++;
        }
        else{
            //System.out.println("Instruction at index " + Integer.toString(instructionIndex) + " has been fully executed");
        }
    }
}