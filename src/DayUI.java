import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;
import java.util.List;

public class DayUI {

    private StackPane stack;
    private VBox dayBox;
    private VBox ul;

    public DayUI(CustomDate currentDate, CustomCalendar calendar) {
        stack = new StackPane();
        dayBox = new VBox();
        dayBox.setPadding(new Insets(0, 2, 0, 2));
        dayBox.minHeight(500.0);
        dayBox.maxWidth(200);
        dayBox.setPadding(new Insets(10, 5, 10, 5));
        if (currentDate.isToday()) {
            dayBox.setStyle("-fx-background-color: bcedc4;");
        } else {
            dayBox.setStyle("-fx-background-color: eee;");
        }

        Rectangle r = new Rectangle();
        r.setX(0.0f);
        r.setY(0.0f);
        r.setWidth(200.0f);
        r.setHeight(500.0f);
        r.setFill(Color.web("EEEEEE"));

        VBox header = new VBox();
        header.setAlignment(Pos.BASELINE_CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        Text title = new Text();
        title.setText(currentDate.getWeekday());
        title.setFont(Font.font("Segone UI", 22));

        Text subtitle = new Text();
        subtitle.setText(currentDate.getDayAndMonth());
        subtitle.setFont(Font.font("Segone UI", 17));

        header.getChildren().addAll(title, subtitle);

        ul = new VBox(10);
        ScrollPane ulScroll = new ScrollPane(ul);
        ulScroll.setPrefViewportHeight(325);

        ul.setMaxWidth(175);
        ul.setMinHeight(325);

        List<Event> events = calendar.getEventNamesByDate(currentDate.getDate());

        setEvents(events, calendar, currentDate);

        TextField textField = new TextField();
        textField.setTooltip(new Tooltip("Enter info"));

        Button btn = new Button();
        btn.setPrefWidth(170);
        btn.setStyle("-fx-background-color: #6EB437; -fx-text-fill: #FFF");
        btn.setPadding(new Insets(5, 5, 5, 5));
        btn.setText("Add Event");
        btn.setOnAction(event -> {
            if (!textField.getText().isEmpty()) {
                calendar.addEvent(new Event(currentDate.getDate(), textField.getText()));
                textField.setText("");
                setEvents(calendar.getEventNamesByDate(currentDate.getDate()), calendar, currentDate);
            }
        });

        VBox btnWrapper = new VBox(btn);
        btnWrapper.setPadding(new Insets(10, 0, 0, 0));
        btnWrapper.setAlignment(Pos.CENTER);

        dayBox.getChildren().addAll(header, ulScroll, textField, btnWrapper);

        stack.getChildren().add(r);
        stack.getChildren().add(dayBox);
    }

    public StackPane getStack() {
        return stack;
    }

    private void setEvents(List<Event> events, CustomCalendar calendar, CustomDate currentDate) {
        ul.getChildren().clear();

        for (Event event : events) {
            HBox li = new HBox();
            li.setPadding(new Insets(6, 0, 0, 10));

            Text txt = new Text();
            txt.setWrappingWidth(130);
            txt.setText(event.getContent());

            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);

            Button rmBtn = new Button();
            rmBtn.setText("\uD83D\uDDD1\uFE0F");

            rmBtn.setOnAction(rmEvent -> {
                calendar.removeEvent(event);
                setEvents(calendar.getEventNamesByDate(currentDate.getDate()), calendar, currentDate);
            });
            li.getChildren().addAll(txt, r, rmBtn);
            ul.getChildren().add(li);
        }
    }
}
