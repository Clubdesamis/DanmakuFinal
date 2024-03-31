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
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_MAKECIRCLE_INTERNAL_ID;
    }

    public boolean execute(){
        double trajectory = initialOffset;
        double offset = (double)360.0 / (double)projectileCount;
        double trajectorySinus;
        for(int i = 0; i < projectileCount; i++){
            //trajectorySinus = (double)Math.cos(Math.toRadians((double)trajectory));
            Game.projectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)trajectory)), (double)(speed * Math.cos(Math.toRadians((double)trajectory))), (double)(speed * Math.sin(Math.toRadians((double)trajectory))), image, projectileSize, id);
            trajectory += offset;
        }
        return true;
    }

    public static Instruction build(String[] arguments){
        switch(arguments.length){
            case 7 ->{
                //System.out.println("\n\n\n\n\n Bwuh \n\n\n\n\n");
                return new InstructionMakeCircle(Integer.parseInt(arguments[POSITION_X]), Integer.parseInt(arguments[POSITION_Y]), Integer.parseInt(arguments[PROJECTILE_COUNT]), Double.parseDouble(arguments[INITIAL_OFFSET]), Double.parseDouble(arguments[SPEED]), Game.ressourceManager.getTexture(arguments[IMAGE]), Integer.parseInt(arguments[ID]));
                //break;
            }
            default ->{
                System.out.println("Invalid argument count passed in builder of MakeCircle");
                break;
            }
        }
        return null;
    }


}