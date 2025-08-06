import java.io.*;

public class FileReadExample {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("test_8_6.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }

        br.close();
    }
}