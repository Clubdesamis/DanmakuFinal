import java.awt.image.BufferedImage;

public class InstructionMakeCircle extends Instruction {
    private int positionX;
    private int positionY;
    private int projectileCount;
    private double initialOffset;
    private double speed;
    private int projectileSize;
    private int id;
    private BufferedImage image;

    private boolean fetchEnemyPositionX;
    private boolean fetchEnemyPositionY;


    private final double FIRING_DISTANCE = 0;

    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int PROJECTILE_COUNT = 2;
    public static final int INITIAL_OFFSET = 3;
    public static final int SPEED = 4;
    public static final int IMAGE = 5;
    public static final int ID = 6;

    public InstructionMakeCircle(int positionX, int positionY, int projectileCount, double initialOffset, double speed, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.initialOffset = initialOffset;
        this.speed = speed;
        this.image = image;
        fetchEnemyPositionX = false;
        fetchEnemyPositionY = false;
    }

    public void setFetchPositionX(boolean b){
        fetchEnemyPositionX = b;
    }

    public void setFetchPositionY(boolean b){
        fetchEnemyPositionY = b;
    }

    public InstructionMakeCircle(int positionX, int positionY, int projectileCount, double initialOffset, double speed, BufferedImage image, int id){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.initialOffset = initialOffset;
        this.speed = speed;
        this.image = image;
        //this.projectileSize = projectileSize;
        this.projectileSize = image.getHeight();
        this.id = id;
        fetchEnemyPositionX = false;
        fetchEnemyPositionY = false;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_MAKECIRCLE_INTERNAL_ID;
    }

    public boolean execute(){


        if(fetchEnemyPositionX){
            positionX = (int)Game.enemyCharacter.getPositionX();
        }

        if(fetchEnemyPositionY){
            positionY = (int)Game.enemyCharacter.getPositionY();
        }

        double trajectory = initialOffset;
        double offset = (double)360.0 / (double)projectileCount;
        double trajectorySinus;
        for(int i = 0; i < projectileCount; i++){
            //trajectorySinus = (double)Math.cos(Math.toRadians((double)trajectory));
            Game.enemyProjectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)trajectory)), (double)(speed * Math.cos(Math.toRadians((double)trajectory))), (double)(speed * Math.sin(Math.toRadians((double)trajectory))), image, projectileSize, id);
            trajectory += offset;
        }
        return true;
    }

    public static Instruction build(String[] arguments){

        InstructionMakeCircle instruction = null;
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
            case 7 ->{
                //System.out.println("\n\n\n\n\n Bwuh \n\n\n\n\n");
                instruction = new InstructionMakeCircle(_positionX, _positionY, Integer.parseInt(arguments[PROJECTILE_COUNT]), Double.parseDouble(arguments[INITIAL_OFFSET]), Double.parseDouble(arguments[SPEED]), Game.ressourceManager.getTexture(arguments[IMAGE]), Integer.parseInt(arguments[ID]));
                //break;
            }
            default ->{
                System.out.println("Invalid argument count passed in builder of MakeCircle");
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