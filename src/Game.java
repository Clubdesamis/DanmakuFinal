import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

    public static SceneManager sceneManager;
    public static JFrame window;
    public static Graphics graphics;
    public static double deltaTime;
    public static double msPerFrame;
    public static BufferStrategy bufferStrategy;

    public static void init(){
        window = new JFrame();
        window.setVisible(true);
        window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.createBufferStrategy(2);
        window.setIgnoreRepaint(true);
        sceneManager = new SceneManager();
        bufferStrategy = window.getBufferStrategy();
    }

    public static void loop(){
        try{
            while(true){

                Thread.sleep(16);
                sceneManager.simulate();
                sceneManager.render(window.getGraphics());

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




}
