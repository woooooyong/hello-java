import com.sun.net.httpserver.*;
import com.google.gson.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class RestApiServerV7 {
    public static void main(String[] args) throws Exception {
        UserDAO userDAO = new UserDAO();   // DAO 준비
        Gson gson = new Gson();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // ✅ POST /user → 등록
        server.createContext("/user", exchange -> {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");

            // --- POST /user ---
            if (method.equals("POST") && parts.length == 2) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                User newUser = gson.fromJson(isr, User.class);
                userDAO.insertUser(newUser);

                String response = "{\"status\":\"saved\"}";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }

            // --- GET /user/{id} ---
            else if (method.equals("GET") && parts.length == 3) {
                int id = Integer.parseInt(parts[2]);
                User user = userDAO.getUserById(id);
                String response = (user != null) ? gson.toJson(user) : "{}";

                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }

            // --- PUT /user/{id} ---
            else if (method.equals("PUT") && parts.length == 3) {
                int id = Integer.parseInt(parts[2]);
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                User updateUser = gson.fromJson(isr, User.class);
                updateUser.setId(id);

                boolean success = userDAO.updateUser(updateUser);
                String response = success ? "{\"status\":\"updated\"}" : "{\"status\":\"fail\"}";

                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }

            // --- DELETE /user/{id} ---
            else if (method.equals("DELETE") && parts.length == 3) {
                int id = Integer.parseInt(parts[2]);
                boolean success = userDAO.deleteUser(id);
                String response = success ? "{\"status\":\"deleted\"}" : "{\"status\":\"fail\"}";

                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }

            else {
                String response = "{\"error\":\"Unsupported request\"}";
                exchange.sendResponseHeaders(400, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        // ✅ GET /users → 전체 조회
        server.createContext("/users", exchange -> {
            if (exchange.getRequestMethod().equals("GET")) {
                List<User> users = userDAO.getUsers();
                String response = gson.toJson(users);

                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        server.start();
        System.out.println("✅ Server running on http://localhost:8080");
    }
}
