import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import java.io.*;
import java.net.InetSocketAddress;

public class RestApiServerV4 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/user", new UserHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("REST API 서버 시작: http://localhost:8000/user");
    }

    static class UserHandler implements HttpHandler {
        private Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String response = "";

            if ("POST".equalsIgnoreCase(method)) {
                // 1. 요청 Body 읽기
                BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestBody.append(line);
                }
                br.close();

                // 2. JSON → User 객체 변환
                User user = gson.fromJson(requestBody.toString(), User.class);

                // 3. 응답 객체 생성
                ResponseWrapper result = new ResponseWrapper("ok", user);

                // 4. 객체 → JSON 변환
                response = gson.toJson(result);
            } else {
                response = gson.toJson(new ResponseWrapper("error", null));
            }

            // JSON 응답
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

// User 클래스 (User.java 따로 두어도 됨)
class User {
    int id;
    String name;
}

// 응답 포맷을 위한 Wrapper 클래스
class ResponseWrapper {
    String status;
    User user;

    ResponseWrapper(String status, User user) {
        this.status = status;
        this.user = user;
    }
}
