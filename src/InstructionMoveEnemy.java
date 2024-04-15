public class InstructionMoveEnemy extends Instruction{

	private double targetPositionX;
	private double targetPositionY;
	private double speed;

	public static final int POSITION_X = 0;
	public static final int POSITION_Y = 1;
	public static final int SPEED = 2;

	public InstructionMoveEnemy(double positionX, double positionY, double speed){
		this.targetPositionX = positionX;
		this.targetPositionY = positionY;
		this.speed = speed;

	}


	@Override
	public int getInstructionId() {
		return Constants.INSTRUCTION_MOVE_ENEMY_INTERNAL_ID;
	}

	@Override
	public boolean execute() {

		double tempPositionX = Game.enemyCharacter.getPositionX();
		double tempPositionY = Game.enemyCharacter.getPositionY();
		if((Game.enemyCharacter.getPositionX() == targetPositionX) && (Game.enemyCharacter.getPositionY() == targetPositionY)){
			return true;
		}
		if((Game.enemyCharacter.getTargetPositionX() == targetPositionX) && (Game.enemyCharacter.getTargetPositionY() == targetPositionY) && (Game.enemyCharacter.getSpeedScalar() == targetPositionX)){
			return false;
		}

		Game.enemyCharacter.setSpeed(speed);
		Game.enemyCharacter.setTargetPosition(targetPositionX, targetPositionY);
		return false;
	}

	public static Instruction build(String[] arguments){
		switch(arguments.length){
			case 3 -> {
				return new InstructionMoveEnemy(Double.parseDouble(arguments[POSITION_X]), Double.parseDouble(arguments[POSITION_Y]), Double.parseDouble(arguments[SPEED]));
			}
		}
		return null;
	}
}
