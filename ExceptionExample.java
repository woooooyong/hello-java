public class ExceptionExample {

    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2 ,3};
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("인덱스 초과 에러: " + e.getMessage());
        }finally {
            System.out.println("프로그램 종료");
        }
    }
}