import java.awt.image.BufferedImage;

public class InstructionMakeSpiral extends Instruction {

    private int positionX;
    private int positionY;
    private int projectileCount;
    private int projectilesToShoot;
    private int projectileSize;
    private double projectileSpeed;
    private int firingSpeed;
    private double rotationSpeed;
    private boolean clockwise;
    private int internalID;
    private double initialOffset;
    private BufferedImage image;

    private boolean fetchEnemyPositionX;
    private boolean fetchEnemyPositionY;

    private final double FIRING_DISTANCE = 50;

    int frameCount = 0;

    int projectilesShot = 0;
    double offset = (double)0.0;

    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int POSITION_PROJECTILE_COUNT = 2;
    public static final int POSITION_PROJECTILES_TO_SHOOT = 3;
    public static final int POSITION_PROJECTILE_SPEED = 4;
    public static final int POSITION_FIRING_SPEED = 5;
    public static final int POSITION_ROTATION_SPEED = 6;
    public static final int POSITION_INITIAL_OFFSET = 7;
    public static final int POSITION_CLOCKWISE = 8;
    public static final int POSITION_IMAGE = 9;
    public static final int POSITION_ID = 10;

    public InstructionMakeSpiral(int positionX, int positionY, int projectileCount, int projectilesToShoot, double projectileSpeed, int firingSpeed, double rotationSpeed, double initialOffset, boolean clockwise, BufferedImage image, int id){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.projectilesToShoot = projectilesToShoot;
        this.projectileSize = image.getHeight();
        this.projectileSpeed = projectileSpeed;
        this.firingSpeed = firingSpeed;
        this.rotationSpeed = rotationSpeed;
        this.initialOffset = initialOffset;
        this.clockwise = clockwise;
        this.internalID = id;
        this.image = image;
        fetchEnemyPositionX = false;
        fetchEnemyPositionY = false;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_MAKESPIRAL_INTERNAL_ID;
    }

    public void setFetchPositionY(boolean b) {
        this.fetchEnemyPositionY = b;
    }

    public void setFetchPositionX(boolean b) {
        this.fetchEnemyPositionX = b;
    }
    public boolean execute(){

        if(fetchEnemyPositionX){
            positionX = (int)Game.enemyCharacter.getPositionX();
        }

        if(fetchEnemyPositionY){
            positionY = (int)Game.enemyCharacter.getPositionY();
        }

        frameCount++;
        if(frameCount == firingSpeed){
            double _trajectory = initialOffset + offset;
            double _tempOffset = (double)360.0 / (double)projectileCount;

            //Game.enemyProjectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)_trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)_trajectory)), (double)(projectileSpeed * Math.cos(Math.toRadians((double)_trajectory))), (double)(projectileSpeed * Math.sin(Math.toRadians((double)_trajectory))), image, projectileSize, id);
            //Game.enemyProjectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)_trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)_trajectory)), Math.cos(Math.toRadians((double)_trajectory)), Math.sin(Math.toRadians((double)_trajectory)), projectileSpeed, image, projectileSize, id);
            //_trajectory += _tempOffset;

            double trajectory = initialOffset + offset;
            double tempOffset = (float)360.0 / (float)projectileCount;

            for(int i = 0; i < projectileCount; i++){
                Game.enemyProjectileManager.assignProjectile(positionX + FIRING_DISTANCE * (float)Math.cos(Math.toRadians((double)trajectory)), positionY + FIRING_DISTANCE * (float)Math.sin(Math.toRadians((double)trajectory)), Math.cos(Math.toRadians((double)trajectory)), Math.sin(Math.toRadians((double)trajectory)), projectileSpeed, image, projectileSize, id);
                trajectory += tempOffset;

            }

            frameCount = 0;
            if(clockwise){
                offset += rotationSpeed;
            }
            else{
                offset -= rotationSpeed;
            }
            projectilesShot++;
        }
        if(projectilesShot == projectilesToShoot){
            cleanVariables();
            return true;
        }
        return false;
    }

    public void cleanVariables(){
        frameCount = 0;
        projectilesShot = 0;
        offset = (double)0.0;
        //System.out.println("Variables cleaned!");
    }


    public static Instruction build(String[] arguments){

        InstructionMakeSpiral instruction = null;
        int _positionX;
        int _positionY;

        if(arguments[POSITION_X].equals(Constants.ARGUMENT_ENEMY_POSITION_X)){
            _positionX = 0;
        }
        else{
            _positionX = Integer.parseInt(arguments[POSITION_X]);
        }

        if(arguments[POSITION_Y].equals(Constants.ARGUMENT_ENEMY_POSITION_Y)){
            _positionY = 0;
        }
        else{
            _positionY = Integer.parseInt(arguments[POSITION_Y]);
        }

        switch(arguments.length){
            case 11 ->{
                instruction = new InstructionMakeSpiral(_positionX, _positionY, Integer.parseInt(arguments[POSITION_PROJECTILE_COUNT]), Integer.parseInt(arguments[POSITION_PROJECTILES_TO_SHOOT]), Double.parseDouble(arguments[POSITION_PROJECTILE_SPEED]), Integer.parseInt(arguments[POSITION_FIRING_SPEED]), Double.parseDouble(arguments[POSITION_ROTATION_SPEED]), Double.parseDouble(arguments[POSITION_INITIAL_OFFSET]), Boolean.parseBoolean(arguments[POSITION_CLOCKWISE]), Game.ressourceManager.getTexture(arguments[POSITION_IMAGE]), Integer.parseInt(arguments[POSITION_ID]));
                //break;
            }
            default ->{
                System.out.println("Invalid argument count passed in builder of MakeSpiral");
                break;
            }
        }

        if(arguments[POSITION_X].equals(Constants.ARGUMENT_ENEMY_POSITION_X)){
            instruction.setFetchPositionX(true);
        }
        if(arguments[POSITION_Y].equals(Constants.ARGUMENT_ENEMY_POSITION_Y)){
            instruction.setFetchPositionY(true);
        }

        return instruction;
    }
}