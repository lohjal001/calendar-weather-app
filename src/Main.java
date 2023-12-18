import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CustomCalendar calendar = new CustomCalendar();

        primaryStage.setTitle("Kalender");
        UserInterface UI = new UserInterface(calendar);

        primaryStage.setScene(new Scene(UI.getUI(), 1500, 620));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
