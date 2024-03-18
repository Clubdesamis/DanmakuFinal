import java.util.ArrayList;
import java.util.LinkedList;

public class InstructionParallel extends InstructionContainer{

    private ArrayList<Boolean> finishedList;

    public InstructionParallel(){
        instructions = new ArrayList<Instruction>();
        finishedList = new ArrayList<Boolean>();
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_PARALLEL_INTERNAL_ID;
    }

    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
        finishedList.add(false);
    }

    public boolean execute(){
        boolean finished = true;

        for(int i = 0; i < instructions.size(); i++){
            if(!finishedList.get(i)){
                if(instructions.get(i).execute()){
                    finishedList.set(i, true);
                }
            }
        }

        for(int i = 0; i < finishedList.size(); i++){
            if(!finishedList.get(i)){
                finished = false;
            }
        }
        if(finished){
            for(int i = 0; i < finishedList.size(); i++){
                finishedList.set(i, false);
            }
        }
        return finished;
    }
}