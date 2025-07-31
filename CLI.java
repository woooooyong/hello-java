import java.util.Scanner;

public class CLI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("첫 번째 숫자: ");
        double num1 = scanner.nextDouble();

        System.out.print("연산자 (+, -, *, /): ");
        String operator = scanner.next();

        System.out.print("두 번째 숫자: ");
        double num2 = scanner.nextDouble();

        double result = 0;

        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        } else {
            System.out.println("잘못된 연산자입니다.");
            return;
        }

        System.out.println("결과: " + result);
    }
}