import java.io.*;
import java.net.*;
import org.json.*;

public class ApiJsonArrayExample {

    public static void main(String[] args) {
        try {
            
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("응답 코드: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                
                response.append(line);
            }
            br.close();

            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < 5; i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println("id: " + obj.getInt("id"));
                System.out.println("title: " + obj.getString("title"));
                System.out.println("body: " + obj.getString("body"));
                System.out.println("----");
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}