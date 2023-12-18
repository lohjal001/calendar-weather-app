import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI extends Application {

    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    @Override
    public void start(Stage primaryStage) {
        String city = "Malmö";
        Label weatherLabel = new Label("Weather Information for " + city);

        try {
            URL url = new URL(String.format(API_URL, city, API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String weatherInfo = parseWeatherData(response.toString());
            System.out.println("Weather Information for " + city);
            System.out.println(weatherInfo);

            // Använd weatherInfo för att uppdatera JavaFX-gränssnittet
            weatherLabel.setText(weatherInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        StackPane root = new StackPane();
        root.getChildren().add(weatherLabel);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static String parseWeatherData(String jsonData) {
        System.out.println(jsonData);

        // Lägg till kod för att bearbeta väderdata och få JSon -> String

        return jsonData;
    }

    public static void main(String[] args) {
        launch();
    }
}
