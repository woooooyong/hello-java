import java.io.*;

public class LoadUser_8_6 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_8_6.dat"));
        User_8_6 user_8_6 = (User_8_6) ois.readObject();
        ois.close();

        System.out.println("불러온 객체: " + user_8_6);
    }
}