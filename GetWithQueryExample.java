import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class GetWithQueryExample {
    public static void main(String[] args) {
        HttpURLConnection conn = null;
        try {
            // ✅ 1. 검색어를 URL-safe하게 인코딩
            String query = URLEncoder.encode("자바 네트워크", "utf-8");

            // ✅ 2. 완성된 URL 만들기
            String urlStr = "https://httpbin.org/get?query=" + query + "&page=1";

            // ✅ 3. URL 연결 열기
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // ✅ 4. 응답 코드 확인
            int code = conn.getResponseCode();
            System.out.println("응답 코드: " + code);

            // ✅ 5. 응답 본문 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(
                code >= 200 && code < 300 ? conn.getInputStream() : conn.getErrorStream(), "utf-8"
            ));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // ✅ 6. 응답 파싱
            JSONObject json = new JSONObject(sb.toString());
            JSONObject args = json.getJSONObject("args");

            System.out.println("전송된 쿼리(query): " + args.getString("query"));
            System.out.println("전송된 페이지(page): " + args.getString("page"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
