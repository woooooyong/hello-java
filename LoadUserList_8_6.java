import java.io.*;
import java.util.*;

public class LoadUserList_8_6 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users_8_6.dat"));
        List<User_8_6> users = (List<User_8_6>) ois.readObject(); // 👈 형변환 주의
        ois.close();

        System.out.println("불러온 사용자 목록:");
        for (User_8_6 user : users) {
            System.out.println(user);
        }
    }
}
