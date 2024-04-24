public class InstructionSetTargetSpeed extends Instruction{

	private double targetSpeedScalar;
	private double accelerationScalar;
	private int projectileInternalID;

	public static final int POSITION_TARGET_SPEED_SCALAR = 0;
	public static final int POSITION_ACCELERATION_SCALAR = 1;
	public static final int POSITION_PROJECTILE_INTERNAL_ID = 2;

	public InstructionSetTargetSpeed(double targetSpeedScalar, double accelerationScalar, int projectileInternalID){
		this.targetSpeedScalar = targetSpeedScalar;
		this.accelerationScalar = accelerationScalar;
		this.projectileInternalID = projectileInternalID;
	}

	@Override
	public int getInstructionId() {
		return Constants.INSTRUCTION_SET_TARGET_SPEED_INTERNAL_ID;
	}

	@Override
	public boolean execute() {
		Game.enemyProjectileManager.setTargetSpeedForID(targetSpeedScalar, accelerationScalar, projectileInternalID);
		return true;
	}

	public static Instruction build(String[] arguments){
		switch(arguments.length){
			case 3 -> {
				return new InstructionSetTargetSpeed(Double.parseDouble(arguments[POSITION_TARGET_SPEED_SCALAR]), Double.parseDouble(arguments[POSITION_ACCELERATION_SCALAR]), Integer.parseInt(arguments[POSITION_PROJECTILE_INTERNAL_ID]));
			}
		}
		return null;
	}
}
