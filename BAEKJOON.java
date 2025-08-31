import java.io.*;
import java.util.*;

public class BAEKJOON {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] chess = new String[8];

        for (int i = 0; i < 8; i++){
            chess[i] = br.readLine();
        }

        int count = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(i%2 != Integer.parseInt(chess[i].charAt(j))){
                    count++;
                }
            }
        
        }
        System.out.println(count);
    }
}   
