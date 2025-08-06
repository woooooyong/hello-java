import java.io.*;

public class SaveUser_8_6 {

    public static void main(String[] args) throws IOException{
        User_8_6 user_8_6 = new User_8_6("kimwooyong", 23);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_8_6.dat"));
        oos.writeObject(user_8_6);
        oos.close();

        System.out.println("저장완료");
    }
}