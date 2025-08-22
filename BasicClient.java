import java.io.*;
import java.net.*;

public class BasicClient {

    public static void main(String[] args) {
        
        try {
            
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("Hi server!");
            String response = in.readLine();

            System.out.println("클라이언트가 받은 응답: " + response);
            socket.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}