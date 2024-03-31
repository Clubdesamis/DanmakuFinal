import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

    public static SceneManager sceneManager;
    public static JFrame window;
    public static Graphics graphics;
    public static long deltaTime = 0;
    public static long newTime = 0;
    public static long oldTime = 0;
    public static long timeToSleep = 0;
    public static BufferStrategy bufferStrategy;

    public static ProjectileManager projectileManager;

    public static ProjectileScript projectileScript;
    public static RessourceManager ressourceManager;
    public static ScriptReader scriptReader;

    public static DialogManager dialogManager;

    public static Observable observable;

    public static void init(){
        window = new JFrame();
        window.setVisible(true);
        window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.createBufferStrategy(2);
        window.setIgnoreRepaint(true);
        //window.setOpacity((float)0.0);
        //window.setUndecorated(true);
        //window.setBackground(new Color(0, 0, 0, 0));
        Languages.init();
        dialogManager = new DialogManager();
        dialogManager.loadLanguage(Constants.LANGUAGE);
        sceneManager = new SceneManager();
        bufferStrategy = window.getBufferStrategy();
        ressourceManager = new RessourceManager();
        scriptReader = new ScriptReader();
        projectileManager = new ProjectileManager();
        projectileScript = new ProjectileScript();

        observable = new Observable();
        window.addMouseListener(observable);
        window.addKeyListener(observable);
        window.addMouseMotionListener(observable);
        window.addMouseWheelListener(observable);
    }

    public static void loop(){
        Graphics graphics;
        BufferStrategy bufferStrategy;
        try{
            bufferStrategy = window.getBufferStrategy();
            while(true){
                oldTime = System.nanoTime();

                graphics = bufferStrategy.getDrawGraphics();
                graphics.clearRect(0,0,window.getWidth(), window.getHeight());

                sceneManager.simulate();
                sceneManager.render(graphics);

                graphics.dispose();
                bufferStrategy.show();

                newTime = System.nanoTime();
                deltaTime = newTime - oldTime;
                timeToSleep = Constants.MILLISECONDS_PER_FRAME - deltaTime / 1000000;

                if(timeToSleep <= 0){
                    System.out.println("Invalid timeout value in main loop, defaulting to skipping thread sleep");
                }
                else{
                    Thread.sleep(timeToSleep);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int getWindowWidth(){
        return window.getWidth();
    }

    public static int getWindowHeight(){
        return window.getHeight();
    }

    public static double getDeltaTime(){
        return deltaTime;
    }

    public static SceneManager getSceneManager(){
        return sceneManager;
    }

    public static JFrame getWindowFrame(){
        return window;
    }

    public static Graphics getGraphics(){
        return bufferStrategy.getDrawGraphics();
        //return window.getBufferStrategy().getDrawGraphics();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Game.init();
        Game.getSceneManager().add("testScene", new DanmakuScene());
        Game.getSceneManager().add("signInScene", new SignInScene());
        Game.getSceneManager().add("mainMenuScene", new MainMenuScene());
        Game.getSceneManager().push("signInScene");
        Game.loop();
    }
}