import java.awt.*;

public interface Scene {

    public void init();
    public void render(Graphics graphics);
    public void simulate();
    public void close();
    public void setName(String name);
}
