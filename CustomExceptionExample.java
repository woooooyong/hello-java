public class CustomExceptionExample {

    public static void main(String[] args) {
        int age = -10;

        if (age <0 ){
            throw new NegativeAgeException("age can not be less than zero");
        }

        System.out.println("age is nomal :" + age);
    }
}