public abstract class Instruction {
    public int id;

    abstract public int getInstructionId();


    abstract public boolean execute();
}
