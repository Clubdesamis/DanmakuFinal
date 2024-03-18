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
            return;
        }
        if(instructions[instructionIndex].execute()){
            instructionIndex++;
        }
        else{
        }
    }

    public void jumpToLastLoop(){

        while(--instructionIndex >= 0){
            if(instructions[instructionIndex].getInstructionId() == Constants.INSTRUCTION_LOOP_INTERNAL_ID){
                break;
            }
        }
    }

    public void jumpToOffset(int offset){
        instructionIndex += offset;
    }
}