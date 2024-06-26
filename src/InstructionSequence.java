import java.util.ArrayList;

public class InstructionSequence extends InstructionContainer{

    private int currentIndex;

    public InstructionSequence(){
        instructions = new ArrayList<Instruction>();
        currentIndex = 0;
    }


    @Override
    public int getInstructionId() {
        return Constants.INSTRUCTION_SEQUENCE_INTERNAL_ID;
    }

    public boolean execute(){
        if(instructions.get(currentIndex).execute()){
            //System.out.println("Instruction" + currentIndex + " executed!");
            currentIndex++;
            if(currentIndex == instructions.size()){
                currentIndex = 0;
                return true;
            }
        }
        return false;
    }
}