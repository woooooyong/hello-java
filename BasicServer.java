import java.io.*;
import java.net.*;

public class BasicServer {
    public static void main(String[] args) {
        
        try {

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("서버: 클라이언트 접속 대기 중...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("서버: 클라이언트 연결됨!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = in.readLine();

            System.out.println("서버가 받은 메시지: " + message );

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("서버가 받은 메시지: " + message);

            clientSocket.close();
            serverSocket.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
