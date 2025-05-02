package currencyConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyAPI {

    public static double getLiveRate(String from, String to) {
        try {
            String urlStr = "https://open.er-api.com/v6/latest/" + from;
            System.out.println("Calling: " + urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
    
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
    
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
    
            reader.close();
            JSONObject obj = new JSONObject(response.toString());
    
            if (obj.has("rates")) {
                JSONObject rates = obj.getJSONObject("rates");
                return rates.getDouble(to);
            } else {
                System.out.println("Unexpected response: " + obj.toString());
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return 0.0;
    }
}    