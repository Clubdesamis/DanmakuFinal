public class ProjectileScript {
    public Instruction[] instructions;

    private int instructionIndex = 0;
    private int instructionCount = 0;

    private ScriptHeader scriptHeader;
    private boolean finished;


    public ProjectileScript(){
        instructions = new Instruction[Constants.SCRIPT_SIZE];
        finished = false;
        scriptHeader = null;
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
            finished = true;
            return;
        }
        if(instructions[instructionIndex].execute()){
            instructionIndex++;
        }
        else{
        }
    }

    public void setScriptHeader(ScriptHeader scriptHeader){
        this.scriptHeader = scriptHeader;
    }

    public ScriptHeader getScriptHeader(){
        return scriptHeader;
    }

    public void clearScript(){
        instructionIndex = 0;
        instructionCount = 0;
        finished = false;
    }

    public boolean isFinished(){
        return finished;
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