import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class RestApiServerV5 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/users", new UsersHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("REST API 서버 시작: http://localhost:8000/users");
    }

    static class UsersHandler implements HttpHandler {
        private Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String response = "";

            if ("GET".equalsIgnoreCase(method)) {
                // 1. 사용자 리스트 생성
                List<User> users = new ArrayList<>();
                users.add(new User(1, "홍길동"));
                users.add(new User(2, "김우용"));
                users.add(new User(3, "이순신"));

                // 2. 리스트 → JSON 배열 변환
                response = gson.toJson(users);

            } else {
                response = gson.toJson("지원하지 않는 메서드");
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

// User 클래스
class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
