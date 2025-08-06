import java.io.*;
import java.util.*;

public class SaveUserList_8_6 {

    public static void main(String[] args) throws IOException{
        List<User_8_6> user_8_6 =  new ArrayList<>();
        user_8_6.add(new User_8_6("우용", 25));
        user_8_6.add(new User_8_6("민지", 24));
        user_8_6.add(new User_8_6("지훈", 23));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users_8_6.dat"));
        
        oos.writeObject(user_8_6);  // 리스트 전체를 한 번에 저장
        oos.close();

        System.out.println("리스트 저장 완료");
    }
}   