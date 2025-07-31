import java.util.Scanner;

public class LoopTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("how many do Loop? ");
        int count = scanner.nextInt();

        for (int i = 1; i <= count; i++){
            System.out.println(i + "times Looping");
        }
    }
}
