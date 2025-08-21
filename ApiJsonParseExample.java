import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ApiJsonParseExample {

    public static void main(String[] args) {
        try {
            
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            
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

            JSONObject json = new JSONObject(response.toString());
            System.out.println("userId: " + json.getInt("userId"));
            System.out.println("id: " + json.getInt("id"));
            System.out.println("title: " + json.getString("title"));
            System.out.println("body: " + json.getString("body"));


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}