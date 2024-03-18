public class InstructionLoopOld extends Instruction {

    public int originalIterationCount;
    public int iterationCount;
    public int jumpOffset;

    public InstructionLoopOld(){
        this.iterationCount = 0;
        this.originalIterationCount = 0;
        this.jumpOffset = 0;
    }

    public InstructionLoopOld(int iterationCount, int jumpOffset){
        this.iterationCount = iterationCount - 1;
        this.originalIterationCount = iterationCount - 1;
        this.jumpOffset = jumpOffset;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_LOOP_INTERNAL_ID;
    }

    public boolean execute(){
        if(iterationCount <= 0){
            iterationCount = originalIterationCount;
            return true;
        }
        else{
            iterationCount--;
            Game.projectileScript.jumpToOffset(jumpOffset);
            return false;
        }
    }

}
