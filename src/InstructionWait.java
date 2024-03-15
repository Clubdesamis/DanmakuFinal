public class InstructionWait  extends Instruction {

    private int timeToWait;
    private boolean firstPass = true;
    private int time = 0;

    public InstructionWait(int timeToWait){
        this.timeToWait = timeToWait;
    }
    public boolean execute(){
        if(firstPass){
            time = (int)System.currentTimeMillis();
            firstPass = false;
            return false;
        }
        else if(((int)System.currentTimeMillis() - time) >= timeToWait){
            return true;
        }
        return false;
    }
}
