import java.util.ArrayList;

public abstract class InstructionContainer extends Instruction {

	protected ArrayList<Instruction> instructions;

	public void addInstruction(Instruction instruction){
		instructions.add(instruction);
	}
}