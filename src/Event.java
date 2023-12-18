import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Serializable {
    private LocalDate date;
    private String content;

    public Event(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }
}
