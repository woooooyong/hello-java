public class ThrowsExample {

    public static void main(String[] args) throws InterruptedException{
        System.out.println("3초 후 실행...");
        Thread.sleep(3000); // 3초 지연
        System.out.println("완료!");
    }
}