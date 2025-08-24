import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import java.io.*;
import java.net.InetSocketAddress;

class User {
    int id;
    String name;
}

public class RestApiServerV3 {
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
                // 1. Body 읽기
                BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestBody.append(line);
                }
                br.close();

                // 2. JSON → User 객체 변환
                User user = gson.fromJson(requestBody.toString(), User.class);
                System.out.println("서버가 받은 객체: id=" + user.id + ", name=" + user.name);

                // 3. 객체 사용해서 응답 작성
                response = "안녕하세요, " + user.name + " (id=" + user.id + ")";
            } else {
                response = "지원하지 않는 메서드";
            }

            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
