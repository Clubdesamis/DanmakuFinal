public class InstructionWait extends Instruction {

    private int timeToWait;
    private boolean firstPass = true;
    private int time = 0;

    public InstructionWait(int timeToWait){
        this.timeToWait = timeToWait;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_WAIT_INTERNAL_ID;
    }
    public boolean execute(){
        if(firstPass){
            time = (int)System.currentTimeMillis();
            firstPass = false;
            return false;
        }
        else if(((int)System.currentTimeMillis() - time) >= timeToWait){
            firstPass = true;
            return true;
        }
        return false;
    }

    public static Instruction build(String[] arguments){
        return new InstructionWait(Integer.parseInt(arguments[0]));
    }
}