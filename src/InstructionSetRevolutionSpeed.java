public class InstructionSetRevolutionSpeed extends Instruction{

	private double revolutionCenterX;
	private double revolutionCenterY;
	private double revolutionSpeed;
	private int projectileInternalID;
	private boolean fetchEnemyPositionX;
	private boolean fetchEnemyPositionY;


	public static final int POSITION_REVOLUTION_CENTER_X = 0;
	public static final int POSITION_REVOLUTION_CENTER_Y = 1;
	public static final int POSITION_REVOLUTION_SPEED = 2;
	public static final int POSITION_PROJECTILE_INTERNAL_ID = 3;


	public InstructionSetRevolutionSpeed(double revolutionCenterX, double revolutionCenterY, double revolutionSpeed, int projectileInternalID){
		this.revolutionCenterX = revolutionCenterX;
		this.revolutionCenterY = revolutionCenterY;
		this.revolutionSpeed = revolutionSpeed;
		this.projectileInternalID = projectileInternalID;
		fetchEnemyPositionX = false;
		fetchEnemyPositionY = false;
	}

	public void setFetchPositionX(boolean b){
		fetchEnemyPositionX = b;
	}
	public void setFetchPositionY(boolean b){
		fetchEnemyPositionY = b;
	}

	@Override
	public int getInstructionId() {
		return Constants.INSTRUCTION_SET_REVOLUTION_SPEED_INTERNAL_ID;
	}

	@Override
	public boolean execute() {

		if(fetchEnemyPositionX){
			revolutionCenterX = (int)Game.enemyCharacter.getPositionX();
		}

		if(fetchEnemyPositionY){
			revolutionCenterY = (int)Game.enemyCharacter.getPositionY();
		}

		Game.enemyProjectileManager.setRevolutionSpeedForID(revolutionCenterX, revolutionCenterY, revolutionSpeed, projectileInternalID);
		return true;
	}

	public static Instruction build(String[] arguments){

		InstructionSetRevolutionSpeed instruction = null;

		double _positionX;
		double _positionY;

		if(arguments[POSITION_REVOLUTION_CENTER_X].equals(Constants.ARGUMENT_ENEMY_POSITION_X)){
			_positionX = 0;
		}
		else{
			_positionX = Double.parseDouble(arguments[POSITION_REVOLUTION_CENTER_X]);
		}

		if(arguments[POSITION_REVOLUTION_CENTER_Y].equals(Constants.ARGUMENT_ENEMY_POSITION_Y)){
			_positionY = 0;
		}
		else{
			_positionY = Double.parseDouble(arguments[POSITION_REVOLUTION_CENTER_Y]);
		}

		switch(arguments.length){
			case 4 ->{
				instruction = new InstructionSetRevolutionSpeed(_positionX, _positionY, Double.parseDouble(arguments[POSITION_REVOLUTION_SPEED]), Integer.parseInt(arguments[POSITION_PROJECTILE_INTERNAL_ID]));
			}
			default ->{
				System.out.println("Invalid argument count passed in builder of setRevolutionSpeed");
				break;
			}
		}

		if(arguments[POSITION_REVOLUTION_CENTER_X].equals(Constants.ARGUMENT_ENEMY_POSITION_X)){
			instruction.setFetchPositionX(true);
		}
		if(arguments[POSITION_REVOLUTION_CENTER_Y].equals(Constants.ARGUMENT_ENEMY_POSITION_Y)){
			instruction.setFetchPositionY(true);
		}

		return instruction;
	}
}
