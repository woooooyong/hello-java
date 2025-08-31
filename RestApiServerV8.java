import com.sun.net.httpserver.*;
import com.google.gson.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class RestApiServerV8 {

        public static void main(String[] args) throws Exception {
            UserService userService = new UserService();
            Gson gson = new Gson();

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/user", exchange ->{

                String method = exchange.getRequestMethod();
                String path = exchange.getRequestURI().getPath();
                String[] parts = path.split("/");

                if (method.equals("POST") && parts.length == 2){
                    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                    User newUser = gson.fromJson(isr, User.class);
                    try {
                        userService.addUser(newUser);
                        sendResponse(exchange, 200, "{\"status\":\"saved\"}");
                    } catch (IllegalArgumentException e) {
                        sendResponse(exchange, 400, "{\"error\":\"" + e.getMessage() + "\"}");
                    }
                }

                else if (method.equals("GET") && parts.length == 3){
                    int id = Integer.parseInt(parts[2]);
                    User user = userService.getUser(id);
                    String response = (user != null) ? gson.toJson(user) : "{}";
                    sendResponse(exchange, 200, response);
                }

                else if (method.equals("PUT") && parts.length == 3){
                    int id = Integer.parseInt(parts[2]);
                    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                    User updateUser = gson.fromJson(isr, User.class);
                    updateUser.setId(id);

                    boolean success = userService.updateUser(updateUser);
                    String response = success ? "{\"status\":\"updated\"}" : "{\"status\":\"fail\"}";
                    sendResponse(exchange, 200, response);
                }

                else if (method.equals("DELETE") && parts.length == 3){
                    int id = Integer.parseInt(parts[2]);
                    boolean success = userService.deleteUser(id);
                    String response = success ? "{\"status\":\"deleted\"}" : "{\"status\":\"fail\"}";
                    sendResponse(exchange, 200, response);
                }

                else {
                    sendResponse(exchange, 400, "{\"error\":\"Unsupported request\"}");
                }
            });

            server.createContext("/users", exchange -> {
                if (exchange.getRequestMethod().equals("GET")) {
                    List<User> users = userService.getAllUsers();
                    String response = gson.toJson(users);
                    sendResponse(exchange, 200, response);
                }
            });

            server.start();
            System.out.println("✅ Server running on http://localhost:8080");
        }

        private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
}