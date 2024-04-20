import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Observable implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

    public enum EventID {
        MOUSE_CLICKED,
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_ENTERED,
        MOUSE_EXITED,
        MOUSE_DRAGGED,
        MOUSE_MOVED,
        MOUSE_WHEEL_MOVED,
        KEY_TYPED,
        KEY_PRESSED,
        KEY_RELEASED
    }

    private Hashtable<EventID, ArrayList<Observer>> observers;

    public Observable() {
        observers = new Hashtable<>();
    }

    public void add(Observer o, EventID... ids) {
        for(EventID id : ids) {
            ArrayList<Observer> list = observers.get(id);
            if(list == null) {
                list = new ArrayList<>();
                observers.put(id, list);
            }

            list.add(o);
        }
    }

    public void clearAll() {
        for(var kv : observers.entrySet()) {
            kv.getValue().clear();
        }
    }

    public void notify(EventID id, AWTEvent event) {
        ArrayList<Observer> list = observers.get(id);
        if(list != null) {
            for (Observer o : list) {
                o.notification(id, event);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        notify(EventID.MOUSE_CLICKED, e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notify(EventID.MOUSE_PRESSED, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notify(EventID.MOUSE_RELEASED, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        notify(EventID.MOUSE_ENTERED, e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        notify(EventID.MOUSE_EXITED, e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        notify(EventID.KEY_TYPED, e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notify(EventID.KEY_PRESSED, e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notify(EventID.KEY_RELEASED, e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        notify(EventID.MOUSE_DRAGGED, e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        notify(EventID.MOUSE_MOVED, e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        notify(EventID.MOUSE_WHEEL_MOVED, e);
    }
}