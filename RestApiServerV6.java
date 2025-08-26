import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class RestApiServerV6 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/user", new UserPostHandler());
        server.createContext("/users", new UserGetHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("REST API 서버 시작: http://localhost:8000");
    }

    // POST /user → DB에 User 저장
    static class UserPostHandler implements HttpHandler {
        private Gson gson = new Gson();
        private UserDAO dao = new UserDAO();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) requestBody.append(line);
                br.close();

                User u = gson.fromJson(requestBody.toString(), User.class);
                dao.insertUser(u);

                String response = gson.toJson("User 저장 완료: " + u.name);
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    // GET /users → DB에서 전체 User 조회
    static class UserGetHandler implements HttpHandler {
        private Gson gson = new Gson();
        private UserDAO dao = new UserDAO();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                List<User> users = dao.getUsers();

                String response = gson.toJson(users);
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
