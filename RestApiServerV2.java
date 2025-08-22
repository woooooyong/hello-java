import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class RestApiServerV2 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/user", new UserHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("REST API 서버 시작: http://localhost:8000/user");
    }

    static class UserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String response = "";

            if ("POST".equalsIgnoreCase(method)) {
                // 📌 1. 요청 Body 읽기
                InputStream is = exchange.getRequestBody();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestBody.append(line);
                }
                br.close();

                System.out.println("클라이언트가 보낸 데이터: " + requestBody.toString());

                // 📌 2. 간단히 응답 (실제로는 JSON 파싱해서 DB 저장 등 처리)
                response = "{\"status\":\"ok\",\"data\":" + requestBody.toString() + "}";
            } else {
                response = "{\"error\":\"지원하지 않는 메서드\"}";
            }

            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
