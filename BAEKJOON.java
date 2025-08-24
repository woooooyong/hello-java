import java.io.*;
import java.util.*;

public class BAEKJOON {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for (String pattern : croatia){
            word = word.replace(pattern, "*");
        }

        System.out.println(word.length());
    }
}