import java.awt.*;
import java.io.IOException;

public interface Scene {

    public void init() throws IOException;
    public void close();
    public void render(Graphics graphics);
    public void simulate();
    public void setName(String name);
}