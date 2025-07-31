import java.util.Scanner;

public class Variables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("당신의 나이는? ");
        int age = scanner.nextInt();

        if (age < 10) {
            System.out.println("초딩이네?");
        } else if (age < 20) {
            System.out.println("학생이구나!");
        } else if (age < 65) {
            System.out.println("사회인이네.");
        } else {
            System.out.println("연세가 있으시네요.");
        }
    }
}
