import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

public class SceneManager {

    public Hashtable<String, Scene> scenes;
    public LinkedList<Scene> sceneStack;
    private int scenesToRender = 1;
    private int scenesToSimulate = 1;

    public SceneManager(){
        scenes = new Hashtable<String, Scene>();
        sceneStack = new LinkedList<Scene>();
    }

    public void setScenesToRender(int count){
        if(count <= sceneStack.size()){
            this.scenesToRender = count;
        }
        else{
            System.out.println("Invalid value passed in setScenesToRender in SceneManager");
        }
    }

    public void add(String name, Scene scene){
        scene.setName(name);
        scenes.put(name, scene);
    }

    public void push(String name) throws IOException {
        if(scenes.containsKey(name)){
            scenes.get(name).init();
            sceneStack.add(scenes.get(name));
            return;
        }
        System.out.println("Invalid scene key name in SceneManager.push()");
    }

    public void pop(){
        if(sceneStack.size() > 0){
            sceneStack.getLast().close();
            sceneStack.removeLast();
        }
    }

    public void render(Graphics graphics){

        for(int i = sceneStack.size() - scenesToRender; i < sceneStack.size(); i++){
            sceneStack.get(i).render(graphics);
        }

        //sceneStack.getFirst().render(graphics);


        //for(int i = 0; i < sceneStack.size(); i++){
            //sceneStack.get(i).render(graphics);
        //}
    }

    public void simulate(){

        for(int i = sceneStack.size() - scenesToSimulate; i < sceneStack.size(); i++){
            sceneStack.get(i).simulate();
        }

        //sceneStack.getFirst().simulate();
        //for(int i = 0; i < sceneStack.size(); i++){
            //sceneStack.get(i).simulate();
        //}
    }

}