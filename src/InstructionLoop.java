import java.util.ArrayList;

public class InstructionLoop extends InstructionContainer{
    private int originalIterationCount;
    private int iterationCount;
    private int currentIndex;

    public InstructionLoop(int iterationCount){
        instructions = new ArrayList<Instruction>();
        currentIndex = 0;
        this.iterationCount = iterationCount;
        this.originalIterationCount = iterationCount;
    }

    public InstructionLoop(){
        instructions = new ArrayList<Instruction>();
        currentIndex = 0;
        this.iterationCount = -1;
        this.originalIterationCount = -1;
    }

    public void setIterationCount(int iterationCount){
        this.iterationCount = iterationCount;
        this.originalIterationCount = iterationCount;
    }


    public boolean execute(){
        if(instructions.get(currentIndex).execute()){
            //System.out.println("Instruction" + currentIndex + " executed!");
            currentIndex++;
            if(currentIndex == instructions.size()){
                iterationCount--;
                if(iterationCount == 0){
                    iterationCount = originalIterationCount;
                    return true;
                }
                else{
                    currentIndex = 0;
                    return false;
                }
            }
        }
        return false;
    }


    public int getInstructionId(){
        return Constants.INSTRUCTION_LOOP_INTERNAL_ID;
    }
}