import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * bxl
 * 2019/8/1 22:19
 */
public class EventSource {

    private String name;
    private List<ClickEventListener> listeners = new ArrayList<>();

    public EventSource(){
        this.name = "你好";
        System.out.println("原名："+this.name);
    }

    public void addListener(ClickEventListener clickEventListener){
        this.listeners.add(clickEventListener);
    }

    public void removeListener(ClickEventListener clickEventListener){
        if(!this.listeners.isEmpty())
            this.listeners.remove(clickEventListener);
    }

    protected void notifies(){
        ClickEventListener listener = null;
        for (ClickEventListener eventListener : this.listeners){
            listener = eventListener;
            listener.fireClickEvent(new ClickEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifies();
    }
}
