import java.io.*;
import java.net.*;

public class ApiClientPost {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8000/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 📌 POST 요청으로 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // Body 보낼 때 필요

            // 📌 JSON 데이터 작성
            String jsonInput = "{\"id\":2,\"name\":\"김우용\"}";

            // 📌 요청 Body에 쓰기
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 📌 응답 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line.trim());
            }
            in.close();

            System.out.println("서버 응답: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
