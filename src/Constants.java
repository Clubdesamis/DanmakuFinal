public class Constants {
    public static final int WINDOW_HEIGHT = 960;
    public static final int WINDOW_WIDTH = 1080;

    //Millisecondes entre chaque frame refresh
    public static final int MILLISECONDS_PER_FRAME = 16;
    public static final int ENEMY_PROJECTILE_BUFFER_SIZE = 1000;
    public static final int PLAYER_PROJECTILE_BUFFER_SIZE = 200;

    public static final int SCRIPT_SIZE = 500;

    public static final String LANGUAGE = "arabic";

    public static final String TEXTURE_FOLDER = "Textures";
    public static final String ANIMATED_SPRITE_FOLDER = "Textures/AnimatedSprites";
    public static final String SCRIPT_FOLDER = "Scripts";
    public static final String CONFIG_FOLDER = "Config";



    public static final String CONFIG_UP_KEY_TAG = "UpKey";
    public static final String CONFIG_DOWN_KEY_TAG = "DownKey";
    public static final String CONFIG_LEFT_KEY_TAG = "LeftKey";
    public static final String CONFIG_RIGHT_KEY_TAG = "RightKey";
    public static final String CONFIG_SHOOT_KEY_TAG = "ShootKey";
    public static final String CONFIG_LANGUAGE_TAG = "Language";
    public static final String META_TAG = "<META>";
    public static final String META_END_TAG = "</META>";

    public static final String SCRIPT_TAG = "<SCRIPT>";

    public static final String SCRIPT_END_TAG = "</SCRIPT>";

    public static final String LOOP_TAG = "<LOOP>";
    public static final String LOOP_END_TAG = "</LOOP>";

    public static final String PARALLEL_TAG = "<PARALLEL>";
    public static final String PARALLEL_END_TAG = "</PARALLEL>";

    public static final String SEQUENCE_TAG = "<SEQUENCE>";

    public static final String SEQUENCE_END_TAG = "</SEQUENCE>";

    public static final String INSTRUCTION_WAIT_TAG = "wait";
    public static final String INSTRUCTION_MAKE_CIRCLE_TAG = "makeCircle";
    public static final String INSTRUCTION_MAKE_SPIRAL_TAG = "makeSpiral";
    public static final String INSTRUCTION_MOVE_ENEMY_TAG = "moveEnemy";
    public static final String INSTRUCTION_SET_TARGET_SPEED_TAG = "setTargetSpeed";
    public static final String INSTRUCTION_SET_REVOLUTION_SPEED_TAG = "setRevolutionSpeed";

    public static final String ARGUMENT_ENEMY_POSITION_X = "enemyPositionX";
    public static final String ARGUMENT_ENEMY_POSITION_Y = "enemyPositionY";

    public static final String[] META_ARGUMENTS = {"Name", "Difficulty", "Creator"};

    public static final int defaultLifeCount = 5;

    public static final int INSTRUCTION_LOOP_INTERNAL_ID = 1;
    public static final int INSTRUCTION_PARALLEL_INTERNAL_ID = 2;

    public static final int INSTRUCTION_SEQUENCE_INTERNAL_ID = 3;
    public static final int INSTRUCTION_WAIT_INTERNAL_ID = 4;

    public static final int INSTRUCTION_MAKECIRCLE_INTERNAL_ID = 10;
    public static final int INSTRUCTION_MAKESPIRAL_INTERNAL_ID = 11;
    public static final int INSTRUCTION_MOVE_ENEMY_INTERNAL_ID = 12;
    public static final int INSTRUCTION_SET_TARGET_SPEED_INTERNAL_ID = 13;
    public static final int INSTRUCTION_SET_REVOLUTION_SPEED_INTERNAL_ID = 14;

    public static final char DEFAULT_UP_KEYCODE = 'w';
    public static final char DEFAULT_DOWN_KEYCODE = 's';
    public static final char DEFAULT_LEFT_KEYCODE = 'a';
    public static final char DEFAULT_RIGHT_KEYCODE = 'd';
    public static final char DEFAULT_SHOOT_KEYCODE = '.';

    public static final String DEFAULT_LANGUAGE = "English";

    public static final int GAME_WIDTH = 960;
    public static final int GAME_HEIGHT = 1280;


    public static final int MENU_LABEL_HEIGHT = 50;
    public static final int MENU_LABEL_WIDTH = 200;
}