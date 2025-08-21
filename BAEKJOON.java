import java.io.*;
import java.util.*;

public class BAEKJOON {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        int n = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String S = st.nextToken();

            for(int j = 0; j < S.length(); j++){
                for(int k = 0; k <num; k++){
                    System.out.print(S.charAt(j));
                }
            }  
            System.out.println(); 

        }
    }
}