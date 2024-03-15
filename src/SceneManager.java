import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

public class SceneManager {

    public Hashtable<String, Scene> scenes;
    public LinkedList<Scene> sceneStack;

    public SceneManager(){
        scenes = new Hashtable<String, Scene>();
        sceneStack = new LinkedList<Scene>();
    }

    public void add(String name, Scene scene){
        scene.setName(name);
        scenes.put(name, scene);
    }

    public void push(String name) throws IOException {
        if(scenes.containsKey(name)){
            scenes.get(name).init();
            sceneStack.push(scenes.get(name));
            return;
        }
        System.out.println("Invalid scene key name in SceneManager.push()");
    }

    public void render(Graphics graphics){
        sceneStack.getFirst().render(graphics);
        //for(int i = 0; i < sceneStack.size(); i++){
            //sceneStack.get(i).render(graphics);
        //}
    }

    public void simulate(){
        sceneStack.getFirst().simulate();
        //for(int i = 0; i < sceneStack.size(); i++){
            //sceneStack.get(i).simulate();
        //}
    }

}
