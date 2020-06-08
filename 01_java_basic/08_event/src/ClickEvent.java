import java.util.EventObject;

/**
 * bxl
 * 2019/8/1 22:17
 */
public class ClickEvent extends EventObject {

    private EventSource eventSource;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ClickEvent(EventSource source) {
        super(source);
        this.eventSource = source;
    }
}
