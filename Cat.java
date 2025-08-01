public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(getName() + "가 야옹합니다.");
    }
}