import java.awt.*;
import java.util.EventListener;

/**
 * bxl
 * 2019/8/1 22:20
 */
public class ClickEventListener implements EventListener {

    public void fireClickEvent(ClickEvent e){
        EventSource source = (EventSource)e.getSource();
        System.out.println("点击事件");
        System.out.println("改名:"+source.getName());
        String tt = new String();

    }

}
