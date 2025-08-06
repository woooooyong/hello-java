public class ThrowExample {

    public static void main(String[] args) {
        int age = -5;

        if (age < 0) {
            throw new IllegalArgumentException("나이는 음수가 될 수 없습니다");
        }

        System.out.println("나이 : "+ age);
    }
}