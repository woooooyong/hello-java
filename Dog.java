public class Dog extends Animal {
    public Dog(String name) {
        super(name); // 부모 생성자 호출
    }

    @Override
    public void sound() {
        System.out.println(getName() + "가 멍멍 짖습니다.");
    }
}
