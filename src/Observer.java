import java.awt.*;

public interface Observer {
    void notification(Observable.EventID id, AWTEvent e);
}