/**
 * bxl
 * 2019/8/1 22:42
 */
public class MainTest {

    public static void main(String[] args){
        EventSource source = new EventSource();
        source.addListener(new ClickEventListener(){
            @Override
            public void fireClickEvent(ClickEvent e){
                super.fireClickEvent(e);
            }
        });

        source.setName("好不好");
    }
}
