import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Game.init();
        Game.getSceneManager().add("testScene", new TestScene());
        Game.getSceneManager().push("testScene");
        Game.loop();
    }
}