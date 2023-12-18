import cn.hutool.json.JSONObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherApp {

    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public static void main(String[] args) {
        String city = "Malmö";
        JSONObject obj = new JSONObject();


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

            System.out.println("Weather Information for " + city);
            System.out.println(parseWeatherData(response.toString()));

            JsonObject jo = Json.parse(response.toString()).asObject();
            System.out.println(jo);

            JsonArray ja = jo.get("weather").asArray();
            System.out.println(ja);

            JsonObject jo2 = ja.get(0).asObject();
            System.out.println(jo2);

            String s = jo2.getString("description", "missing");
            System.out.println(s);


            System.out.println(jo.get("weather").asArray().get(0).asObject().getString("description", "missing"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseWeatherData(String jsonData) {
        System.out.println(jsonData);


        return jsonData;
    }
}