import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RestApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // /user 엔드포인트 등록
        server.createContext("/user", new UserHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("REST API 서버 시작: http://localhost:8000/user");
    }

    static class UserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod(); // GET, POST 등 확인
            String response = "";

            if ("GET".equalsIgnoreCase(method)) {
                response = "{\"id\":1, \"name\":\"홍길동\"}";
            } else if ("POST".equalsIgnoreCase(method)) {
                response = "{\"message\":\"새 사용자 추가됨\"}";
            } else {
                response = "{\"error\":\"지원하지 않는 메서드\"}";
            }

            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            System.out.println(response);
            os.close();
        }
    }
}
